package com.project_rabbit.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Integer createUser(User user);

    Optional<User> findUser(Integer userId);

    User updateUser(Integer userId,
            User user);

    List<User> getAllUsers();

    void deleteUser(Integer userId);
}