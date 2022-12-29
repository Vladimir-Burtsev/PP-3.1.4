package com.burtsev.pp_course.controller;

import com.burtsev.pp_course.model.User;
import com.burtsev.pp_course.service.RoleService;
import com.burtsev.pp_course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController_v2 {
    private final UserService usersService;
    private final RoleService roleService;


    @Autowired
    public AdminController_v2(UserService usersService, RoleService roleService) {
        this.usersService = usersService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(Model model){
        model.addAttribute("users", usersService.getAllUsers());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("userCurrent", usersService.getCurrentUser());

        return "admin_v2/admin_view";
    }
//    @GetMapping("/user/{id}")
//    public String showUser(@PathVariable("id") int id, Model model){
//        model.addAttribute("user", usersService.getUser(id));
//        return "admin_v2/show_user";
//    }
    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "admin_v2/admin_view";

        usersService.save(user);
        return "redirect:/admin";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "admin_v2/admin_view";

        usersService.update(user);
        System.out.println(user.getUsername());
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        usersService.delete(id);
        return "redirect:/admin";
    }
}
