package BlogSystem.BlogSystem.controllers;

import BlogSystem.BlogSystem.dto.requests.AddUserRequest;
import BlogSystem.BlogSystem.dto.requests.UpdateUserRequest;
import BlogSystem.BlogSystem.dto.responses.GetAllUserResponse;
import BlogSystem.BlogSystem.entities.User;
import BlogSystem.BlogSystem.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public GetAllUserResponse getAll() {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public User createOneUser(@RequestBody AddUserRequest newUser) {
        return userService.saveOneUser(newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId) {
        this.userService.deleteOneUserById(userId);
    }

    @PutMapping("{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateOneUser(userId, updateUserRequest);

    }


}
