package com.gridsocial.service;
import com.gridsocial.model.GroupRequest;
import com.gridsocial.repository.GroupRequestRepository;
import com.gridsocial.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupRequestService {

    @Autowired
    private GroupRequestRepository groupRequestRepository;

    public List<GroupRequest> getAllGroupRequests() {
        return groupRequestRepository.findAll();
    }

    public GroupRequest saveGroupRequest(GroupRequest groupRequest) {
        groupRequest.setStatus("pending"); // Set initial status to 'pending'
        return groupRequestRepository.save(groupRequest);
    }

    public GroupRequest updateGroupRequest(Long id, GroupRequest groupRequest) {
        GroupRequest existingRequest = groupRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));
        existingRequest.setStatus(groupRequest.getStatus());
        return groupRequestRepository.save(existingRequest);
    }


    public void deleteGroupRequest(Long id) {
        groupRequestRepository.deleteById(id);
    }

    public GroupRequest getGroupRequestById(Long id) {
        return groupRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("Group Request not found"));
    }
}