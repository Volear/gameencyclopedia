package ge.tsu.gameencyclopedia.review;

import ge.tsu.gameencyclopedia.game.Game;
import ge.tsu.gameencyclopedia.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GameService gameService;

    @Transactional
    public ReviewDTO saveReview(String author, Integer rating, String content, Long gameId) {

        Game game = gameService.findById(gameId);

        Review review = new Review();
        review.setAuthor(author);
        review.setRating(rating);
        review.setContent(content);
        review.setGame(game);
        review = reviewRepository.save(review);

        return ReviewDTO.fromReview(review);
    }
}