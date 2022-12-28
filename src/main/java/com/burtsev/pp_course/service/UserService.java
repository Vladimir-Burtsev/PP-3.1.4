package com.burtsev.pp_course.service;

import com.burtsev.pp_course.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser (int id);
    void save(User user);
    void update(User updatedUser);
    void delete(int id);
    User getCurrentUser();
}
