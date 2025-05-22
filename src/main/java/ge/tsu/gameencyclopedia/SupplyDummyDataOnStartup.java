package ge.tsu.gameencyclopedia;

import ge.tsu.gameencyclopedia.game.Game;
import ge.tsu.gameencyclopedia.game.GameRepository;
import ge.tsu.gameencyclopedia.review.Review;
import ge.tsu.gameencyclopedia.review.ReviewRepository;
import ge.tsu.gameencyclopedia.user.User;
import ge.tsu.gameencyclopedia.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupplyDummyDataOnStartup implements CommandLineRunner {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            // Create admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@gameencyclopedia.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);

            // Create regular users
            User user1 = new User();
            user1.setUsername("johndoe");
            user1.setEmail("john@example.com");
            user1.setPassword(passwordEncoder.encode("password123"));
            user1.setFirstName("John");
            user1.setLastName("Doe");
            user1.setRole(User.Role.USER);
            userRepository.save(user1);

            User user2 = new User();
            user2.setUsername("janesmith");
            user2.setEmail("jane@example.com");
            user2.setPassword(passwordEncoder.encode("password123"));
            user2.setFirstName("Jane");
            user2.setLastName("Smith");
            user2.setRole(User.Role.USER);
            userRepository.save(user2);

            System.out.println("Sample users have been created!");
        }

        if (gameRepository.count() == 0) {
            Game game1 = new Game();
            game1.setTitle("Disco Elysium");
            game1.setDeveloper("ZA/UM");
            game1.setReleaseYear(2019);
            game1.setGenre("Role-playing");
            game1.setDescription("Disco Elysium is set in the district of Martinaise, " +
                    "in and around Terminal B of the Greater Revachol Industrial Harbour, both located in West Revachol, " +
                    "in the Insulinde isola, and taking place in the year '51 of the Current Century.");
            game1 = gameRepository.save(game1);

            Game game2 = new Game();
            game2.setTitle("Darkest Dungeon");
            game2.setDeveloper("Red Hook Studios/Sickhead Games");
            game2.setReleaseYear(2015);
            game2.setGenre("Roguelike/Strategy");
            game2.setDescription("Darkest Dungeon is a hard-core RPG about the stresses of dungeon crawling. " +
                    "You will lead a band of four Heroes on a perilous side-scrolling descent, " +
                    "dealing with a prodigious number of threats to their bodily health, and worse.");
            game2 = gameRepository.save(game2);

            Game game3 = new Game();
            game3.setTitle("Terraria");
            game3.setDeveloper("Re-Logic");
            game3.setReleaseYear(2011);
            game3.setGenre("Action-Adventure");
            game3.setDescription("Terraria is action-adventure sandbox game developed by Re-Logic and published by 505 Games. " +
                    "The game features exploration, combat, crafting, building, " +
                    "and mining inside a procedurally generated 2D world. It has several world difficulties, as well as a character class system.");
            game3 = gameRepository.save(game3);

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