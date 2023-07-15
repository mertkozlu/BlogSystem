package BlogSystem.BlogSystem.service;

import BlogSystem.BlogSystem.dataAccess.PostRepository;
import BlogSystem.BlogSystem.dto.GetAllPostDto;
import BlogSystem.BlogSystem.dto.GetPostByIdDto;
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
        post.setCreationDate(new Date());

        return postRepository.save(post);
    }

    public void deleteOnePostById(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (!CollectionUtils.isEmpty(post.getComments())) {
            throw new BusinessException("Post cannot be deleted while the post has comments.");
        }
        this.postRepository.deleteById(postId);
    }

    public void updateOnePost(UpdatePostRequest updatePostRequest) {
        Post post = postRepository.findById(updatePostRequest.getPostId()).orElseThrow(() -> new BusinessException("Post can not found"));
        Post postToUpdate = this.modelMapperService.forRequest().map(updatePostRequest, Post.class);
        postToUpdate.setCreationDate(post.getCreationDate());
        this.postRepository.save(postToUpdate);

    }

    public GetPostByIdDto getPostByIdDto(Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new BusinessException("Post not found" + postId));
        GetPostByIdDto getPostByIdDto = convertPostGetPostByIdDto(post);
        return getPostByIdDto;

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
        getAllPostsDto.setCreationDate(post.getCreationDate());
        return getAllPostsDto;

    }

    private GetPostByIdDto convertPostGetPostByIdDto(Post post) {
        GetPostByIdDto dto = new GetPostByIdDto();
        dto.setUserId(post.getUser().getUserId());
        dto.setCategoryId(post.getCategory().getCategoryId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setViewCount(post.getViewCount());
        dto.setIsPublished(post.getIsPublished());

        return dto;

    }
}