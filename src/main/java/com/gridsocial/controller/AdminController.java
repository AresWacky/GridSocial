package com.gridsocial.controller;

import com.gridsocial.model.User;
import com.gridsocial.service.UserService;
import com.gridsocial.model.Comment;
import com.gridsocial.service.CommentService;
import com.gridsocial.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

//    @Autowired
//    private GroupService groupService;

    //methods that handle user access

    //gets all users
    @GetMapping("/stats/users/all")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/UserStats";
    }
    //deletes a user
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/all/users";
    }

    //bans a user
    @PostMapping("/user/{id}/ban")
    public String banUser(@PathVariable Long id) {
        userService.banUser(id);
        return "redirect:admin/Report";
    }

    @GetMapping("/stats/user")
    public String getUserCount(Model model) {
        long userCount = userService.getUserCount();
        model.addAttribute("userCount", userCount);
        return "admin/UserStats";
    }


    //Methods that handle comments

    //gets all comments
    @GetMapping("/comments")
    public String getAllComments(Model model) {
        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        return "allComments";
    }


    //Delete comments
    @DeleteMapping("/comments/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "redirect:/comments";
    }



    //Methods for groups
//    @GetMapping("/groups")
//    public String getAllGroups(Model model) {
//        List<Group> groups = groupService.getAllGroups();
//        model.addAttribute("groups", groups);
//        return "allGroups";
//    }



    //get all group member count





    //count groups and users
//    @GetMapping("/stats/all")
//    public String getUsageStatistics(Model model) {
//        long userCount = userService.getUserCount();
//        long groupCount = groupService.countGroups();
//        Map<String, Object> statistics = new HashMap<>();
//        statistics.put("userCount", userCount);
//        statistics.put("groupCount", groupCount);
//        model.addAttribute("statistics", statistics);
//        return "admin/Stats";
//    }
}
