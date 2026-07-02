package com.craigreesedev.demo.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public User getUser(@RequestParam String userName) {
        return userRepository.findByUserName(userName);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/update")
    public User updateUser(@RequestParam Long id,
            @RequestParam String userName,
            @RequestParam String fullName) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        existingUser.setUserName(userName);
        existingUser.setFullName(fullName);

        return userRepository.save(existingUser);
    }
}
