package BlogSystem.BlogSystem.dto;

import BlogSystem.BlogSystem.entities.Post;
import BlogSystem.BlogSystem.entities.User;
import lombok.Data;

import java.util.Date;

@Data
public class GetAllPostDto {
    private Long postId;

    private Long userId;

    private String title;

    private String content;

    private Long viewCount;

    private Date creationDate;

    private Boolean isPublished;

    public GetAllPostDto(Post entity) {
        if (entity != null) {
            this.postId = entity.getPostId();
            User user = entity.getUser();
            if (user != null) {
                this.userId = user.getUserId();
            }
            this.title = entity.getTitle();
            this.content = entity.getContent();
            this.viewCount = entity.getViewCount();
            this.creationDate = entity.getCreationDate();
            this.isPublished = entity.getIsPublished();
        }
    }
}
