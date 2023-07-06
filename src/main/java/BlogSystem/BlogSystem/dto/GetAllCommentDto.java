package patikaOdev.BlogSystem.dto;

import lombok.Data;

import java.util.Date;

@Data

public class GetAllCommentDto {

    private Long userId;
    private Long postId;
    private Long commentId;
    private String comments;
    private Date creationDate;
    private Boolean isConfirmed;
}
