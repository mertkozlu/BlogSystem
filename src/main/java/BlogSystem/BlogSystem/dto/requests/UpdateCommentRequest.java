package patikaOdev.BlogSystem.dto.requests;

import lombok.Data;

@Data
public class UpdateCommentRequest {
    private Long postId;

    private Long userId;

    private String comments;

    private Boolean isConfirmed;
}
