package com.csc340.test.adim;

import com.csc340.test.adim.User.User;
import com.csc340.test.adim.User.UserService;
import com.csc340.test.adim.comment.Comment;
import com.csc340.test.adim.comment.CommentService;
import com.csc340.test.adim.group.Group;
import com.csc340.test.adim.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private GroupService groupService;

    //methods that handle user access

    //gets all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //deletes a user
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //bans a user
    @PostMapping("/user/{id}/ban")
    public void banUser(@PathVariable Long id) {
        userService.banUser(id);
    }

    //Methods that handle comments

    //gets all comments
    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    //Delete comments
    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

    //gets comments by a user
    @GetMapping("/{id}/comments")
    public List<Comment> getCommentsByUserId(@PathVariable Long id) {
        return commentService.getAllComments().stream()
                .filter(comment -> comment.getUserId().equals(id))
                .toList();
    }


    //Methods for groups
    @GetMapping("/groups")
    public List<Group> getAllGroups() {return groupService.getAllGroups();}

    //gets all group member
    @GetMapping("/groups/{groupId}/members")
    public List<User> getGroupMembers(@PathVariable Long groupId) {
        Group group = groupService.getGroupById(groupId);
        return group != null ? List.copyOf(group.getMembers()) : List.of();
    }

    //get all group member count
    @GetMapping("/groups/{groupId}/stats")
    public Map<String, Object> getGroupStatistics(@PathVariable Long groupId) {
        Group group = groupService.getGroupById(groupId);
        Map<String, Object> statistics = new HashMap<>();
        if (group != null) {
            statistics.put("memberCount", group.getMembers().size());
        }
        return statistics;
    }
    //delete a group
    @DeleteMapping("/group/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    //remove a group member
    @DeleteMapping("/{groupId}/members/{memberId}")
    public void removeGroupMember(@PathVariable Long groupId, @PathVariable Long memberId) {
        groupService.removeGroupMember(groupId, memberId);
    }

    //count groups and users
    @GetMapping("/stats")
    public Map<String, Object> getUsageStatistics() {
        long userCount = userService.countCustomers();
        long groupCount = groupService.countGroups();
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("userCount", userCount);
        statistics.put("groupCount", groupCount);
        return statistics;
    }

}