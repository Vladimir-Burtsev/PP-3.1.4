package com.burtsev.pp_course.controller;

import com.burtsev.pp_course.model.User;
import com.burtsev.pp_course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.sasl.AuthenticationException;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    String getUser (@PathVariable("id") int id, Model model) throws AuthenticationException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser.getRoles().stream().allMatch(r-> r.getRolename().equals("ROLE_USER")) && !(currentUser.getId() == id)){
            throw new AuthenticationException();
        }
        model.addAttribute("user", userService.getUser(id));
        return "/users/show_user";
    }
}
