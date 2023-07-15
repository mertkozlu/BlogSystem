package BlogSystem.BlogSystem.service;

import BlogSystem.BlogSystem.dataAccess.CommentRepository;
import BlogSystem.BlogSystem.dto.GetAllCommentDto;
import BlogSystem.BlogSystem.dto.requests.AddCommentRequest;
import BlogSystem.BlogSystem.dto.requests.UpdateCommentRequest;
import BlogSystem.BlogSystem.dto.responses.GetAllCommentResponse;
import BlogSystem.BlogSystem.entities.Comment;
import BlogSystem.BlogSystem.exception.BusinessException;
import BlogSystem.BlogSystem.mapper.ModelMapperService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        comment.setCreationDate(new Date());

        return commentRepository.save(comment);
    }

    public void deleteOneCommentById(Long commentId) {
        this.commentRepository.deleteById(commentId);
    }

    public void updateOneComment(UpdateCommentRequest updateCommentRequest) {
        Comment comment = commentRepository.findById(updateCommentRequest.getCommentId()).orElseThrow(() -> new BusinessException("Comment can not found"));
        Comment commentToUpdate = this.modelMapperService.forRequest().map(updateCommentRequest, Comment.class);
        commentToUpdate.setCreationDate(comment.getCreationDate());
        this.commentRepository.save(commentToUpdate);
    }

    private GetAllCommentDto convertCommentToGetAllCommentDto(Comment comment) {
        GetAllCommentDto getAllCommentDto = new GetAllCommentDto();
        getAllCommentDto.setUserId(comment.getUser().getUserId());
        getAllCommentDto.setPostId(comment.getPost().getPostId());
        getAllCommentDto.setCommentId(comment.getCommentId());
        getAllCommentDto.setComment(comment.getComment());
        getAllCommentDto.setCreationDate(comment.getCreationDate());
        getAllCommentDto.setIsConfirmed(comment.getIsConfirmed());

        return getAllCommentDto;

    }

}
