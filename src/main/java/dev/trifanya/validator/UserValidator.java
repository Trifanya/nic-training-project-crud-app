package dev.trifanya.validator;

import dev.trifanya.model.User;

public class UserValidator {
    private final UserMapper userMapper;
    public void validateNewUser(String email) {
        if (userMapper.findUserByEmail(email).isPresent()) {
            throw new AlreadyExistException("Пользователь с указанным email уже существует.");
        }
    }

    public void validateUpdatedUser(int currentUserId, String email) {
        User namesake = userMapper.findUserByEmail(email).orElse(null);
        if (namesake != null && namesake.getId() != currentUserId) {
            throw new AlreadyExistException("Пользователь с указанным email уже существует.");
        }
    }
}
