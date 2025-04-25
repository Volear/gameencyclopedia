package ge.tsu.gameencyclopedia.game;

import ge.tsu.gameencyclopedia.image.ImageDTO;
import ge.tsu.gameencyclopedia.review.ReviewDTO;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ge.tsu.gameencyclopedia.util.TimeFormatter.prettyFormat;

public class GameDTO {
    private Long id;
    private String title;
    private String developer;
    private Integer releaseYear;
    private String genre;
    private String description;
    private String excerpt;
    private LocalDateTime createTime;
    private String prettyCreateTime;
    private List<ImageDTO> images;
    private List<ReviewDTO> reviews;
    private Double averageRating;

    public static GameDTO fromGame(Game game) {
        GameDTO gameDTO = new GameDTO();
        BeanUtils.copyProperties(game, gameDTO);
        gameDTO.setPrettyCreateTime(prettyFormat(game.getCreateTime()));

        gameDTO.setImages(game.getImages().stream()
                .map(ImageDTO::fromImage)
                .collect(Collectors.toList()));

        gameDTO.setReviews(game.getReviews().stream()
                .map(ReviewDTO::fromReview)
                .collect(Collectors.toList()));

        // Calculate average rating
        double avgRating = game.getReviews().stream()
                .mapToInt(review -> review.getRating())
                .average()
                .orElse(0.0);
        gameDTO.setAverageRating(avgRating);

        return gameDTO;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getPrettyCreateTime() {
        return prettyCreateTime;
    }

    public void setPrettyCreateTime(String prettyCreateTime) {
        this.prettyCreateTime = prettyCreateTime;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}