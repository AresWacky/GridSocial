package com.csc340.test.adim.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return "GroupStats";
    }



    //update group info
    @PutMapping("/group/{id}")
    public Group updateGroup(@PathVariable Long id, @RequestBody Group groupDetails) {
        return groupService.updateGroup(id, groupDetails);
    }
    // delete group
    @DeleteMapping("/group/delete/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }





}
