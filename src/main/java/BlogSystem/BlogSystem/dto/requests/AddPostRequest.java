package BlogSystem.BlogSystem.dto.requests;

import lombok.Data;

@Data
public class AddPostRequest {

    private Long userId;

    private String title;

    private String content;

    private Long viewCount;

    private Boolean isPublished;

}
