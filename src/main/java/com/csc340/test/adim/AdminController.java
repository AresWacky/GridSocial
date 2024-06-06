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

    // methods that handle user access

    // gets all users
    @GetMapping("/stats/users/all")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "UserStats";
    }

    // deletes a user
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/stats/users/all";
    }

    // bans a user
    @PostMapping("/user/{id}/ban")
    public String banUser(@PathVariable Long id) {
        userService.banUser(id);
        return "redirect:/stats/users/all";
    }

    @GetMapping("/stats/user")
    public String getUserCount(Model model) {
        long userCount = userService.getUserCount();
        model.addAttribute("userCount", userCount);
        return "UserStats";
    }

    // Methods that handle comments

    // gets all comments
    @GetMapping("/comments")
    public String getAllComments(Model model) {
        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        return "allComments";
    }

    // Delete comments
    @DeleteMapping("/comments/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "redirect:/comments";
    }

    // gets comments by a user
    @GetMapping("/{id}/comments")
    public String getCommentsByUserId(@PathVariable Long id, Model model) {
        List<Comment> comments = commentService.getAllComments().stream()
                .filter(comment -> comment.getUser().getId().equals(id))
                .toList();
        model.addAttribute("comments", comments);
        return "userComments";
    }

    // Methods for groups
    @GetMapping("/groups")
    public String getAllGroups(Model model) {
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "allGroups";
    }

    // gets all group member
    @GetMapping("/groups/{groupId}/members")
    public String getGroupMembers(@PathVariable Long groupId, Model model) {
        Group group = groupService.getGroupById(groupId);
        List<User> members = group != null ? List.copyOf(group.getMembers()) : List.of();
        model.addAttribute("members", members);
        return "groupMembers";
    }

    // get all group member count
    @GetMapping("/groups/{ groupId}/member-count")
    public String getGroupMemberCount(@PathVariable Long groupId, Model model) {
        Group group = groupService.getGroupById(groupId);
        int memberCount = group != null ? group.getMembers().size() : 0;
        model.addAttribute("memberCount", memberCount);
        return "groupMemberCount";
    }

    // gets all group statistics
    @GetMapping("/stats/groups")
    public String getGroupStatistics(Model model) {
        List<Group> groups = groupService.getAllGroups();
        Map<Group, Integer> groupStatistics = new HashMap<>();
        for (Group group : groups) {
            groupStatistics.put(group, group.getMembers().size());
        }
        model.addAttribute("groupStatistics", groupStatistics);
        return "groupStats";
    }
}





