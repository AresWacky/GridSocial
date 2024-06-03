package com.csc340.test.adim.User;

import com.csc340.test.adim.group.Group;
import com.csc340.test.adim.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    //creates user
    @PostMapping("/create")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    //delete a user
    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //join a group
    @PostMapping("/{userId}/join/{groupId}")
    public User subscribeToGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        return userService.subscribeToGroup(userId, groupId);
    }

    //update a user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    //leave a group
    @PostMapping("/{userId}/leaveGroup/{groupId}")
    public void leaveGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.leaveGroup(userId, groupId);
    }
    //create a group
    @PostMapping("/create/group")
    public Group createGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }


}