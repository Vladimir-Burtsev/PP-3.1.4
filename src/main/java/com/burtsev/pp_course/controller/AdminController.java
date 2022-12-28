//package com.burtsev.pp_course.controller;
//
//import com.burtsev.pp_course.model.User;
//import com.burtsev.pp_course.service.RoleService;
//import com.burtsev.pp_course.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//    private final UserService usersService;
//    private final RoleService roleService;
//
//
//    @Autowired
//    public AdminController(UserService usersService, RoleService roleService) {
//        this.usersService = usersService;
//        this.roleService = roleService;
//    }
//
//    @GetMapping()
//    public String showAllUsers(Model model){
//        model.addAttribute("users", usersService.getAllUsers());
//        return "admin_v2/show_all_users";
//    }
//    @GetMapping("/new")
//    public String newUser(Model model){
//        model.addAttribute("user", new User());
//        model.addAttribute("roles", roleService.getAll());
//        return "admin_v2/new";
//    }
//    @GetMapping("/user/{id}")
//    public String showUser(@PathVariable("id") int id, Model model){
//        model.addAttribute("user", usersService.getUser(id));
//        return "admin_v2/show_user";
//    }
//    @PostMapping
//    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
//        if (bindingResult.hasErrors())
//            return "admin_v2/new";
//
//        usersService.save(user);
//        return "redirect:/admin_v2";
//    }
//    @GetMapping("/user/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id){
//        model.addAttribute("user", usersService.getUser(id));
//        model.addAttribute("roles", roleService.getAll());
//        return "admin_v2/edit";
//    }
//    @PatchMapping("/user/{id}")
//    public String update(@ModelAttribute("user") @Valid User user,
//                         BindingResult bindingResult, @PathVariable("id") int id){
//        if (bindingResult.hasErrors())
//            return "admin_v2/edit";
//
//        usersService.update(user);
//        return "redirect:/admin_v2";
//    }
//    @DeleteMapping("/user/{id}")
//    public String delete(@PathVariable("id") int id){
//        usersService.delete(id);
//        return "redirect:/admin_v2";
//    }
//}
