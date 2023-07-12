package BlogSystem.BlogSystem.dto.requests;

import lombok.Data;

@Data
public class UpdateCommentRequest {

    private Long postId;

    private Long userId;

    private String comment;

    private Boolean isConfirmed;
}
