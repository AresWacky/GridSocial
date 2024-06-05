package com.csc340.test.adim;

import com.csc340.test.adim.User.User;
import com.csc340.test.adim.User.UserService;
import com.csc340.test.adim.comment.Comment;
import com.csc340.test.adim.comment.CommentService;
import com.csc340.test.adim.group.Group;
import com.csc340.test.adim.group.GroupService;
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

    @Autowired
    private GroupService groupService;

    //methods that handle user access

    //gets all users


    @GetMapping("/stats/users/all")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "UserStats";
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
        return "redirect:Report";
    }

    @GetMapping("/stats/user")
    public String getUserCount(Model model) {
        long userCount = userService.getUserCount();
        model.addAttribute("userCount", userCount);
        return "UserStats";
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
    //gets comments by a user
    @GetMapping("/{id}/comments")
    public String getCommentsByUserId(@PathVariable Long id, Model model) {
        List<Comment> comments = commentService.getAllComments().stream()
                .filter(comment -> comment.getUserId().equals(id))
                .toList();
        model.addAttribute("comments", comments);
        return "userComments";
    }


    //Methods for groups
    @GetMapping("/groups")
    public String getAllGroups(Model model) {
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "allGroups";
    }


    //gets all group member
    @GetMapping("/groups/{groupId}/members")
    public String getGroupMembers(@PathVariable Long groupId, Model model) {
        Group group = groupService.getGroupById(groupId);
        List<User> members = group != null ? List.copyOf(group.getMembers()) : List.of();
        model.addAttribute("members", members);
        return "groupMembers";
    }

    //get all group member count
    @GetMapping("/groups/{groupId}/stats")
    public String getGroupStatistics(@PathVariable Long groupId, Model model) {
        Group group = groupService.getGroupById(groupId);
        Map<String, Object> statistics = new HashMap<>();
        if (group != null) {
            statistics.put("memberCount", group.getMembers().size());
        }
        model.addAttribute("statistics", statistics);
        return "groupStats";
    }
    //delete a group
    @DeleteMapping("/group/{id}")
    public String deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return "redirect:/groups";
    }


    //remove a group member
    @DeleteMapping("/{groupId}/members/{memberId}")
    public String removeGroupMember(@PathVariable Long groupId, @PathVariable Long memberId) {
        groupService.removeGroupMember(groupId, memberId);
        return "redirect:/groups/" + groupId + "/members";
    }


    //count groups and users
    @GetMapping("/stats/all")
    public String getUsageStatistics(Model model) {
        long userCount = userService.getUserCount();
        long groupCount = groupService.countGroups();
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("userCount", userCount);
        statistics.put("groupCount", groupCount);
        model.addAttribute("statistics", statistics);
        return "Stats";
    }
}