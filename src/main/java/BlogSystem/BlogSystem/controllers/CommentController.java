package patikaOdev.BlogSystem.controllers;

import org.springframework.web.bind.annotation.*;
import patikaOdev.BlogSystem.dto.requests.AddCommentRequest;
import patikaOdev.BlogSystem.dto.requests.AddPostRequest;
import patikaOdev.BlogSystem.dto.requests.UpdateCommentRequest;
import patikaOdev.BlogSystem.dto.responses.GetAllCommentResponse;
import patikaOdev.BlogSystem.entities.Comment;
import patikaOdev.BlogSystem.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }
    @GetMapping("/getAll")
    public GetAllCommentResponse getAll() {
        return commentService.getAllComments();
    }

    @PostMapping("/add")
    public Comment createOnePost(@RequestBody AddCommentRequest newComment){
        return commentService.saveOneComment(newComment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId) {
        this.commentService.deleteOneCommentById(commentId);
    }
    @PutMapping("{commentId}")
    public Comment updateOneUser(@PathVariable  Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest){
        return commentService.updateOneComment(commentId, updateCommentRequest);

    }
}
