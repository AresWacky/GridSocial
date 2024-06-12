package com.csc340.test.adim.group;

import com.csc340.test.adim.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class GroupOwnerController {

    @Autowired
    private GroupService groupService;

    // create a group
    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }
    


    @GetMapping("/group/all")
    public String getAllGroups(Model model) {
        List<Group> groupList = groupService.getAllGroups();
        int groupCount = groupList.size();
        model.addAttribute("groupList", groupList);
        model.addAttribute("groupCount", groupCount);
        return "Admin/GroupStats";
    }



    //update group info
    @PutMapping("/group/{id}")
    public Group updateGroup(@PathVariable Long id, @RequestBody Group groupDetails) {
        return groupService.updateGroup(id, groupDetails);
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
        return "Admin/GroupStats";
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



}
