package BlogSystem.BlogSystem.controllers;

import BlogSystem.BlogSystem.dto.requests.AddPostRequest;
import BlogSystem.BlogSystem.dto.requests.UpdatePostRequest;
import BlogSystem.BlogSystem.dto.responses.GetAllPostResponse;
import BlogSystem.BlogSystem.entities.Post;
import BlogSystem.BlogSystem.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getAll")
    public GetAllPostResponse getAll() {
        return postService.getAllPosts();
    }

    @PostMapping("/add")
    public Post createOnePost(@RequestBody AddPostRequest newPost) {
        return postService.saveOnePost(newPost);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId) {
        this.postService.deleteOnePostById(postId);
    }

    @PutMapping("{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest) {
        return postService.updateOnePost(postId, updatePostRequest);

    }
}
