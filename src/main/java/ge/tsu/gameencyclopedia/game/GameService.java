package ge.tsu.gameencyclopedia.game;

import ge.tsu.gameencyclopedia.image.Image;
import ge.tsu.gameencyclopedia.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ImageService imageService;

    public List<GameDTO> getAllGames(Pageable pageable) {
        return gameRepository.findAll(pageable).stream()
                .map(GameDTO::fromGame)
                .toList();
    }

    public GameDTO findGameById(Long gameId) {
        Game game = findById(gameId);
        return GameDTO.fromGame(game);
    }

    public Game findById(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found with ID: " + gameId));
    }

    @Transactional
    public GameDTO saveGame(String title, String developer, Integer releaseYear,
                            String genre, String description, List<MultipartFile> imageFiles) {
        Game game = new Game();
        game.setTitle(title);
        game.setDeveloper(developer);
        game.setReleaseYear(releaseYear);
        game.setGenre(genre);
        game.setDescription(description);
        game = gameRepository.save(game);

        if (imageFiles != null && !imageFiles.isEmpty()) {
            List<Image> images = new ArrayList<>();
            for (MultipartFile imageFile : imageFiles) {
                if (imageFile.isEmpty()) {
                    continue;
                }
                String imagePath = imageService.saveImageFile(imageFile);
                Image image = new Image();
                image.setGame(game);
                image.setPath(imagePath);
                images.add(image);
            }
            game.setImages(images);
        }
        game = gameRepository.save(game);

        return GameDTO.fromGame(game);
    }

    public long totalNumberOfGames() {
        return gameRepository.count();
    }

    public List<GameDTO> searchGames(String query, String searchType) {
        List<Game> results;

        switch (searchType) {
            case "title":
                results = gameRepository.findByTitleContainingIgnoreCase(query);
                break;
            case "developer":
                results = gameRepository.findByDeveloperContainingIgnoreCase(query);
                break;
            case "genre":
                results = gameRepository.findByGenreContainingIgnoreCase(query);
                break;
            case "all":
            default:
                results = gameRepository.searchByAnyField(query);
                break;
        }

        return results.stream()
                .map(GameDTO::fromGame)
                .collect(Collectors.toList());
    }
}