package ge.tsu.gameencyclopedia;

import ge.tsu.gameencyclopedia.game.Game;
import ge.tsu.gameencyclopedia.game.GameRepository;
import ge.tsu.gameencyclopedia.review.Review;
import ge.tsu.gameencyclopedia.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupplyDummyDataOnStartup implements CommandLineRunner {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void run(String... args) {
        // Check if database is empty
        if (gameRepository.count() == 0) {
            // Create sample games
            Game game1 = new Game();
            game1.setTitle("The Witcher 3: Wild Hunt");
            game1.setDeveloper("CD Projekt RED");
            game1.setReleaseYear(2015);
            game1.setGenre("RPG");
            game1.setDescription("The Witcher 3: Wild Hunt is an action role-playing game developed by CD Projekt RED. " +
                    "Players control Geralt of Rivia, a monster hunter known as a Witcher. " +
                    "Geralt is looking for his missing adopted daughter, who is on the run from the Wild Hunt.");
            game1 = gameRepository.save(game1);

            Game game2 = new Game();
            game2.setTitle("Red Dead Redemption 2");
            game2.setDeveloper("Rockstar Games");
            game2.setReleaseYear(2018);
            game2.setGenre("Action-Adventure");
            game2.setDescription("Red Dead Redemption 2 is a western-themed action-adventure game. " +
                    "Set in 1899, it follows the story of outlaw Arthur Morgan, a member of the Van der Linde gang. " +
                    "The game features both single-player and online multiplayer components.");
            game2 = gameRepository.save(game2);

            Game game3 = new Game();
            game3.setTitle("God of War");
            game3.setDeveloper("Santa Monica Studio");
            game3.setReleaseYear(2018);
            game3.setGenre("Action-Adventure");
            game3.setDescription("God of War is an action-adventure game that follows Kratos, " +
                    "a former Greek god of war, and his young son Atreus. " +
                    "The game is a soft reboot of the God of War series, and shifts from Greek mythology to Norse mythology.");
            game3 = gameRepository.save(game3);

            // Add reviews
            Review review1 = new Review();
            review1.setAuthor("John Doe");
            review1.setRating(5);
            review1.setContent("One of the best games I've ever played! The story, characters and world are amazing.");
            review1.setGame(game1);
            reviewRepository.save(review1);

            Review review2 = new Review();
            review2.setAuthor("Jane Smith");
            review2.setRating(4);
            review2.setContent("Great gameplay and stunning visuals. The side quests are engaging too.");
            review2.setGame(game1);
            reviewRepository.save(review2);

            Review review3 = new Review();
            review3.setAuthor("Mike Brown");
            review3.setRating(5);
            review3.setContent("The attention to detail in this game is incredible. I was completely immersed in the world.");
            review3.setGame(game2);
            reviewRepository.save(review3);

            System.out.println("Sample data has been loaded!");
        }
    }
}