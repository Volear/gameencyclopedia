package ge.tsu.gameencyclopedia.review;

import jakarta.validation.constraints.*;

public class ReviewForm {
    @NotBlank(message = "Author name must not be blank")
    @Size(max = 50, message = "Author name must not exceed {max} characters")
    private String author;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least {value}")
    @Max(value = 5, message = "Rating must be at most {value}")
    private Integer rating;

    @NotBlank(message = "Review content must not be blank")
    private String content;

    @NotNull(message = "Game ID is required")
    private Long gameId;

    // Getters and Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}