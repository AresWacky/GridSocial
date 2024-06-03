package com.csc340.test.adim.group;

import com.csc340.test.adim.group.Group;
import com.csc340.test.adim.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/groupOwner")
public class GroupOwnerController {

    @Autowired
    private GroupService groupService;

    // create a group
    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }
    
    //mapping to get all members
    @GetMapping("/groups/{groupId}/members")
    public List<User> getGroupMembers(@PathVariable Long groupId) {
        Group group = groupService.getGroupById(groupId);
        return group != null ? List.copyOf(group.getMembers()) : List.of();
    }

    //mapping to view member count
    @GetMapping("/groups/{groupId}/stats")
    public Map<String, Object> getGroupStatistics(@PathVariable Long groupId) {
        Group group = groupService.getGroupById(groupId);
        Map<String, Object> statistics = new HashMap<>();
        if (group != null) {
            statistics.put("memberCount", group.getMembers().size());
        }
        return statistics;
    }


    //update group info
    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable Long id, @RequestBody Group groupDetails) {
        return groupService.updateGroup(id, groupDetails);
    }
    // delete group
    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    // Delete group members
    @DeleteMapping("/{groupId}/members/{memberId}")
    public void removeGroupMember(@PathVariable Long groupId, @PathVariable Long memberId) {
        groupService.removeGroupMember(groupId, memberId);
    }



}
