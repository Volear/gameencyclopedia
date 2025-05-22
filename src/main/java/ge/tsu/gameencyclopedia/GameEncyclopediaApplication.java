package ge.tsu.gameencyclopedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collections;

@SpringBootApplication
public class GameEncyclopediaApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GameEncyclopediaApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8082"));
        app.run(args);
    }
}