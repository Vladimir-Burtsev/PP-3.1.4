package com.burtsev.pp_course.controller;

import com.burtsev.pp_course.model.User;
import com.burtsev.pp_course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService usersService;

    @Autowired
    public UsersController(UserService usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public String showAllUsers(Model model){
        model.addAttribute("users", usersService.getAllUsers());
        return "users/show_all_users";
    }
    @GetMapping("{id}")
    public String showUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", usersService.getUser(id));
        return "users/show_user";
    }
    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "users/new";
    }
    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "users/new";

        usersService.save(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", usersService.getUser(id));
        return "users/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "users/edit";

        usersService.update(user);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        usersService.delete(id);
        return "redirect:/users";
    }
}
