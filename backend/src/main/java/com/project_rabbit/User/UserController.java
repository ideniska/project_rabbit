package com.project_rabbit.User;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Integer> createUser(@RequestBody User user) {
        Integer userId = userService.createUser(user);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findUser(@PathVariable("id") Integer userId) {
        User theCustomer = userService.findUser(userId)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("user with id %s does not exist", userId)));
        return new ResponseEntity<>(theCustomer, HttpStatus.OK);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer userId,
            @RequestBody User user) {
        User theCustomer = userService
                .updateUser(userId, user);
        return new ResponseEntity<>(theCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}