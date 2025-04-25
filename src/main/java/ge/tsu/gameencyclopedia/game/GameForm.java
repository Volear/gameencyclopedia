package ge.tsu.gameencyclopedia.game;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class GameForm {
    @NotBlank(message = "Title must not be blank")
    @Size(max = 100, message = "Title must not exceed {max} characters")
    private String title;

    @NotBlank(message = "Developer must not be blank")
    @Size(max = 50, message = "Developer must not exceed {max} characters")
    private String developer;

    @NotNull(message = "Release year is required")
    @Min(value = 1950, message = "Release year must be at least {value}")
    @Max(value = 2030, message = "Release year must be at most {value}")
    private Integer releaseYear;

    @NotBlank(message = "Genre must not be blank")
    @Size(max = 50, message = "Genre must not exceed {max} characters")
    private String genre;

    @NotBlank(message = "Description must not be blank")
    private String description;

    private List<MultipartFile> images;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
}