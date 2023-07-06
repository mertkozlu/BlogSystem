package patikaOdev.BlogSystem.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import patikaOdev.BlogSystem.dataAccess.CommentRepository;
import patikaOdev.BlogSystem.dataAccess.PostRepository;
import patikaOdev.BlogSystem.dataAccess.UserRepository;
import patikaOdev.BlogSystem.dto.GetAllUsersDto;
import patikaOdev.BlogSystem.dto.requests.AddUserRequest;
import patikaOdev.BlogSystem.dto.requests.UpdateUserRequest;
import patikaOdev.BlogSystem.dto.responses.GetAllUserResponse;
import patikaOdev.BlogSystem.entities.Comment;
import patikaOdev.BlogSystem.entities.Post;
import patikaOdev.BlogSystem.entities.User;
import patikaOdev.BlogSystem.exception.BusinessException;
import patikaOdev.BlogSystem.mapper.ModelMapperService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CommentService commentService;
    private final PostService postService;

    private final ModelMapperService modelMapperService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository, CommentService commentService, PostService postService, ModelMapperService modelMapperService,
                       CommentRepository commentRepository,
                       PostRepository postRepository) {
        this.userRepository = userRepository;
        this.commentService = commentService;
        this.postService = postService;
        this.modelMapperService = modelMapperService;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public GetAllUserResponse getAllUsers() {
        GetAllUserResponse response = new GetAllUserResponse();
        List<GetAllUsersDto> dtos = userRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertUserToGetAllUsersDto)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(dtos)) {
            throw new BusinessException("Empty list");
        }

        response.setGetAllUsersDto(dtos);
        response.setResultCode("1");
        response.setResultDescription("Success");

        return response;


    }


    public User saveOneUser(AddUserRequest newUser) {
        User user = this.modelMapperService.forRequest().map(newUser, User.class);

        return userRepository.save(user);
    }

    public void deleteOneUserById(Long userId) {
        Comment comment = commentRepository.findById(userId).orElse(null);
        Post post = postRepository.findById(userId).orElse(null);
        if (Objects.nonNull(comment) || Objects.nonNull(post)) {
            throw new BusinessException("hhh");
        }
        this.userRepository.deleteById(userId);
    }

    public User updateOneUser(Long userId, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(userId).orElse(null);
        if (Objects.nonNull(user)) {
            user.setUserName(updateUserRequest.getUserName());
            user.setEmail(updateUserRequest.getEmail());
            userRepository.save(user);
            return user;
        }

        throw new BusinessException("User could not found");

    }

    private GetAllUsersDto convertUserToGetAllUsersDto(User user) {
        GetAllUsersDto getAllUsersDto = new GetAllUsersDto();
        getAllUsersDto.setUserId(user.getUserId());
        getAllUsersDto.setUserName(user.getUserName());
        getAllUsersDto.setEmail(user.getEmail());
        getAllUsersDto.setCreationDate(new Date());

        return getAllUsersDto;

    }
}