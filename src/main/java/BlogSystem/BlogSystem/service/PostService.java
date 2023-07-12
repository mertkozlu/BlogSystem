package BlogSystem.BlogSystem.service;

import BlogSystem.BlogSystem.dataAccess.PostRepository;
import BlogSystem.BlogSystem.dto.GetAllPostDto;
import BlogSystem.BlogSystem.dto.requests.AddPostRequest;
import BlogSystem.BlogSystem.dto.requests.UpdatePostRequest;
import BlogSystem.BlogSystem.dto.responses.GetAllPostResponse;
import BlogSystem.BlogSystem.entities.Post;
import BlogSystem.BlogSystem.exception.BusinessException;
import BlogSystem.BlogSystem.mapper.ModelMapperService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapperService modelMapperService;

    public PostService(PostRepository postRepository,
                       ModelMapperService modelMapperService) {
        this.postRepository = postRepository;
        this.modelMapperService = modelMapperService;
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

        Post post = this.modelMapperService.forRequest().map(newPost, Post.class);
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
        GetAllPostDto getAllPostsDto = new GetAllPostDto();
        getAllPostsDto.setUserId(post.getUser().getUserId());
        getAllPostsDto.setCategoryId(post.getCategory().getCategoryId());
        getAllPostsDto.setPostId(post.getPostId());
        getAllPostsDto.setTitle(post.getTitle());
        getAllPostsDto.setContent(post.getContent());
        getAllPostsDto.setViewCount(post.getViewCount());
        getAllPostsDto.setIsPublished(post.getIsPublished());
        getAllPostsDto.setCreationDate(new Date());
        return getAllPostsDto;

    }
}