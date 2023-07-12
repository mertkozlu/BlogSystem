package BlogSystem.BlogSystem.service;

import BlogSystem.BlogSystem.dataAccess.PostRepository;
import BlogSystem.BlogSystem.dataAccess.UserRepository;
import BlogSystem.BlogSystem.dto.GetAllPostDto;
import BlogSystem.BlogSystem.dto.requests.AddPostRequest;
import BlogSystem.BlogSystem.dto.requests.UpdatePostRequest;
import BlogSystem.BlogSystem.dto.responses.GetAllPostResponse;
import BlogSystem.BlogSystem.entities.Post;
import BlogSystem.BlogSystem.entities.User;
import BlogSystem.BlogSystem.exception.BusinessException;
import BlogSystem.BlogSystem.mapper.ModelMapperService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapperService modelMapperService;
    private final UserService userService;

    public PostService(PostRepository postRepository, ModelMapperService modelMapperService,
                       UserService userService) {
        this.postRepository = postRepository;
        this.modelMapperService = modelMapperService;
        this.userService = userService;
    }

    public GetAllPostResponse getAllPosts() {
        GetAllPostResponse response = new GetAllPostResponse();
        List<GetAllPostDto> dtos = postRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertPostToGetAllPostsDto)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(dtos)) {
            throw new BusinessException("Empty list");
        }
        response.setGetAllPostDto(dtos);
        response.setResultCode("1");
        response.setResultDescription("Success");

        return response;
    }

    public Post saveOnePost(AddPostRequest newPost) {
        User user =  userService.getByUserId(newPost.getUserId());
        if (user == null)
            return null;
        Post post = new Post();
        post.setTitle(newPost.getTitle());
        post.setContent(newPost.getContent());
        post.setViewCount(newPost.getViewCount());
        post.setIsPublished(newPost.getIsPublished());
//        Post post = this.modelMapperService.forRequest().map(newPost, Post.class);
        return postRepository.save(post);
    }

    public void deleteOnePostById(Long postId) {
        this.postRepository.deleteById(postId);
    }

    public Post updateOnePost(Long postId, UpdatePostRequest updatePostRequest) {
        Post post = postRepository.findById(postId).orElse(null);
        if (Objects.nonNull(post)) {
            post.setContent(updatePostRequest.getContent());
            post.setTitle(updatePostRequest.getTitle());
            post.setViewCount(updatePostRequest.getViewCount());
            post.setIsPublished(updatePostRequest.getIsPublished());
            postRepository.save(post);
            return post;
        }

        throw new BusinessException("Post could not found");

    }

    private GetAllPostDto convertPostToGetAllPostsDto(Post post) {
        GetAllPostDto getAllPostsDto = new GetAllPostDto(post);
        getAllPostsDto.setUserId(post.getUser().getUserId());
        getAllPostsDto.setPostId(post.getPostId());
        getAllPostsDto.setTitle(post.getTitle());
        getAllPostsDto.setContent(post.getContent());
        getAllPostsDto.setViewCount(post.getViewCount());
        getAllPostsDto.setIsPublished(post.getIsPublished());
        getAllPostsDto.setCreationDate(new Date());
        return getAllPostsDto;

    }
}