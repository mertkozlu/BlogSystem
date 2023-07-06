package patikaOdev.BlogSystem.dto.requests;

import lombok.Data;

@Data
public class UpdatePostRequest {
    private Long userId;
    private Long CommentId;
    private String title;
    private String content;
    private Long viewCount;
    private Boolean isPublished;
}
