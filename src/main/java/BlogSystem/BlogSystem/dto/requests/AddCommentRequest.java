package patikaOdev.BlogSystem.dto.requests;

import lombok.Data;

@Data
public class AddCommentRequest {


    private Long postId;
    private Long userId;

    private String comments;

    private Boolean isConfirmed;
}
