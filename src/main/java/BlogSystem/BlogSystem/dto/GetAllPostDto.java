package BlogSystem.BlogSystem.dto;

import BlogSystem.BlogSystem.entities.Post;
import BlogSystem.BlogSystem.entities.User;
import lombok.Data;

import java.util.Date;

@Data
public class GetAllPostDto {
    private Long postId;

    private Long userId;

    private Long categoryId;

    private String title;

    private String content;

    private Long viewCount;

    private Date creationDate;

    private Boolean isPublished;



}
