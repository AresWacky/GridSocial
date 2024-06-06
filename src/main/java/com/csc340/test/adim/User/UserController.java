package com.csc340.test.adim.User;

import com.csc340.test.adim.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("userList", users);
        return "UserStats"; // Return the view name for listing users
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup"; // Return the view name for signup form
    }

    @PostMapping("/signup")
    public String signUpUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/user/all"; // Redirect to the list of users after signup
    }

    // creates user
    @PostMapping("/create")
    public String createUser(@ModelAttribute User user, Model model) {
        userService.saveUser(user);
        return "redirect:/user/all"; // Redirect to the list of users after creating a user
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/editUser"; // Return the view name for editing a user
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User userDetails, Model model) {
        userService.updateUser(id, userDetails);
        return "redirect:/user/all"; // Redirect to the list of users after updating a user
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/user/all"; // Redirect to the list of users after deleting a user
    }

    @GetMapping("/view/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userView"; // Return the view name for viewing a user's details
    }
}
