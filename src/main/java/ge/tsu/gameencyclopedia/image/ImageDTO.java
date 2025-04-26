package ge.tsu.gameencyclopedia.image;

import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class ImageDTO {
    private Long id;
    private String path;
    private LocalDateTime createTime;

    public static ImageDTO fromImage(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        BeanUtils.copyProperties(image, imageDTO);
        return imageDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}