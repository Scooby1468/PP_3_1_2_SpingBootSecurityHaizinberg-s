package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.MyUser;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAdmin(@RequestParam(required = false) String username, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("username", name);
        return "/admin";
    }

    @GetMapping("/admin/users")
    public String findAll(@RequestParam(required = false) String username, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("username", name);
        List<MyUser> myUsers = userService.findAll();
        model.addAttribute("users", myUsers);
        return "userlist";
    }


    @GetMapping("/admin/user-create")
    public String createUserForm(MyUser myUser, Model model){
        model.addAttribute("new_user", new MyUser());
        return "usercreate";
    }

    @PostMapping("admin/user-create")
    public String createUser(@ModelAttribute("new_user") MyUser myUser){
        myUser.setRoleSet(Collections.singleton(roleService.findById(1L)));
        userService.saveUser(myUser);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        MyUser myUser = userService.findById(id);
        model.addAttribute("user", myUser);
        return "updateuser";
    }

    @PostMapping("/admin/user-update")
    public String updateUser(@ModelAttribute("user") MyUser myUser, HttpServletRequest request){
        myUser.setRoleSet(Collections.singleton(roleService.findById(Long.valueOf(request.getParameter("role")))));
        userService.update(myUser);
        return "redirect:/admin/users";
    }

}
