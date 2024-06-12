package com.csc340.test.adim.GroupRequest;
import com.csc340.test.adim.group.Group;
import com.csc340.test.adim.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/groupRequest")
public class GroupRequestController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRequestService groupRequestService;

    @GetMapping("/all")
    public String getAllGroupRequests(Model model) {
        List<GroupRequest> groupRequests = groupRequestService.getAllGroupRequests();
        model.addAttribute("groupRequests", groupRequests);
        return "Requests";
    }

    @GetMapping("/create")
    public String showCreateGroupRequestForm(Model model) {
        GroupRequest groupRequest = new GroupRequest();
        model.addAttribute("groupRequest", groupRequest);
        return "groupRequestForm";
    }

    @PostMapping("/create")
    public String createGroupRequest(@ModelAttribute GroupRequest groupRequest) {
        groupRequestService.saveGroupRequest(groupRequest);
        return "redirect:/groupRequest/all";
    }

    @PostMapping("/approve/{id}")
    public String approveGroupRequest(@PathVariable Long id) {
        GroupRequest groupRequest = groupRequestService.getGroupRequestById(id);
        if (groupRequest != null) {
            // Create and save the new group
            Group newGroup = new Group();
            newGroup.setName(groupRequest.getGroupName());
            groupService.saveGroup(newGroup);

            // Update the request status to "active"
            groupRequest.setStatus("active");
            groupRequestService.updateGroupRequest(id, groupRequest);
            groupRequestService.deleteGroupRequest(id);
        }
        return "redirect:/groupRequest/all";
    }

    @PostMapping("/deny/{id}")
    public String denyGroupRequest(@PathVariable Long id) {
        groupRequestService.deleteGroupRequest(id);
        return "redirect:/groupRequest/all";
    }
}
