package com.project_rabbit.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;

        @Override
        public Integer createUser(User user) {
                return userRepository.save(user).getUserId();
        }

        @Override
        public Optional<User> findUser(Integer userId) {
                return userRepository.findById(userId);

        }

        @Override
        public List<User> getAllUsers() {
                return userRepository.findAll();
        }

        @Override
        public User updateUser(Integer userId, User user) {
                User theUser = userRepository.findById(userId)
                                .stream()
                                .findFirst()
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                String.format("customer with id %s does not exist",
                                                                                user.getUserId())));

                theUser.setEmail(user.getEmail());

                return userRepository.save(theUser);
        }

        @Override
        public void deleteUser(Integer userId) {
                User theUser = userRepository
                                .findById(userId).get();
                if (theUser == null) {
                        throw new RuntimeException(
                                        String.format("The user with id %s does not exist",
                                                        userId));
                }
                userRepository.delete(theUser);

        }
}
