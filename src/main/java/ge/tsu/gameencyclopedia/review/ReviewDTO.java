package ge.tsu.gameencyclopedia.review;

import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

import static ge.tsu.gameencyclopedia.util.TimeFormatter.prettyFormat;

public class ReviewDTO {
    private Long id;
    private String author;
    private Integer rating;
    private String content;
    private LocalDateTime createTime;
    private String prettyCreateTime;

    public static ReviewDTO fromReview(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        BeanUtils.copyProperties(review, reviewDTO);
        reviewDTO.setPrettyCreateTime(prettyFormat(review.getCreateTime()));
        return reviewDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}