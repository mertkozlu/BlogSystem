package BlogSystem.BlogSystem.controllers;

import BlogSystem.BlogSystem.dto.requests.AddCommentRequest;
import BlogSystem.BlogSystem.dto.requests.UpdateCommentRequest;
import BlogSystem.BlogSystem.dto.responses.GetAllCommentResponse;
import BlogSystem.BlogSystem.entities.Comment;
import BlogSystem.BlogSystem.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @DeleteMapping("/delete/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId) {
        this.commentService.deleteOneCommentById(commentId);
    }

    @PutMapping("/update")
    public void updateOneUser(@RequestBody UpdateCommentRequest updateCommentRequest) {
        this.commentService.updateOneComment(updateCommentRequest);
    }

}
