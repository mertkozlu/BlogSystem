package patikaOdev.BlogSystem.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import patikaOdev.BlogSystem.dataAccess.CommentRepository;
import patikaOdev.BlogSystem.dto.GetAllCommentDto;
import patikaOdev.BlogSystem.dto.requests.AddCommentRequest;
import patikaOdev.BlogSystem.dto.requests.UpdateCommentRequest;
import patikaOdev.BlogSystem.dto.responses.GetAllCommentResponse;
import patikaOdev.BlogSystem.entities.Comment;
import patikaOdev.BlogSystem.exception.BusinessException;
import patikaOdev.BlogSystem.mapper.ModelMapperService;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    private final ModelMapperService modelMapperService;

    public CommentService(CommentRepository commentRepository, ModelMapperService modelMapperService) {
        this.commentRepository = commentRepository;
        this.modelMapperService = modelMapperService;
    }

    public GetAllCommentResponse getAllComments() {
        GetAllCommentResponse response = new GetAllCommentResponse();
        List<GetAllCommentDto> dtos = commentRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertCommentToGetAllCommentDto)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(dtos)) {
            throw new BusinessException("Empty list");
        }
            response.setGetAllCommentDto(dtos);
            response.setResultCode("1");
            response.setResultDescription("Success");

        return response;
    }


    public Comment saveOneComment(AddCommentRequest newComment) {
        Comment comment = this.modelMapperService.forRequest().map(newComment, Comment.class);
        return commentRepository.save(comment);
    }

    public void deleteOneCommentById(Long commentId) {
        this.commentRepository.deleteById(commentId);
    }

    public Comment updateOneComment(Long commentId, UpdateCommentRequest updateCommentRequest) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (Objects.nonNull(comment)) {
            comment.setComment(updateCommentRequest.getComments());
            comment.setIsConfirmed(updateCommentRequest.getIsConfirmed());

            commentRepository.save(comment);
            return comment;
        }

        throw new BusinessException("Comment could not found");

    }

    private GetAllCommentDto convertCommentToGetAllCommentDto(Comment comment) {
        GetAllCommentDto getAllCommentDto = new GetAllCommentDto();
        getAllCommentDto.setUserId(comment.getUser().getUserId());
        getAllCommentDto.setPostId(comment.getPost().getPostId());
        getAllCommentDto.setCommentId(comment.getCommentId());
        getAllCommentDto.setComments(comment.getComment());
        getAllCommentDto.setCreationDate(new Date());
        getAllCommentDto.setIsConfirmed(comment.getIsConfirmed());

        return getAllCommentDto;


    }

}
