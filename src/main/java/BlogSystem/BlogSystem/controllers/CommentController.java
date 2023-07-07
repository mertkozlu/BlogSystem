package BlogSystem.BlogSystem.controllers;

import BlogSystem.BlogSystem.dto.requests.AddCommentRequest;
import BlogSystem.BlogSystem.dto.requests.UpdateCommentRequest;
import BlogSystem.BlogSystem.dto.responses.GetAllCommentResponse;
import BlogSystem.BlogSystem.entities.Comment;
import BlogSystem.BlogSystem.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getAll")
    public GetAllCommentResponse getAll() {
        return commentService.getAllComments();
    }

    @PostMapping("/add")
    public Comment createOnePost(@RequestBody AddCommentRequest newComment) {
        return commentService.saveOneComment(newComment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId) {
        this.commentService.deleteOneCommentById(commentId);
    }

    @PutMapping("{commentId}")
    public Comment updateOneUser(@PathVariable Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest) {
        return commentService.updateOneComment(commentId, updateCommentRequest);
    }

}
