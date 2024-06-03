package com.csc340.test.adim.group;

import com.csc340.test.adim.User.User;
import com.csc340.test.adim.User.UserRepository;
import com.csc340.test.adim.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
   private UserRepository userRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId).orElse(null);
    }

    public long countGroups() {
        return groupRepository.count();
    }

    public Group updateGroup(Long groupId, Group groupDetails) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        group.setName(groupDetails.getName());
        return groupRepository.save(group);
    }

    public void removeGroupMember(Long groupId, Long memberId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));

        Optional<User> optionalUser = userRepository.findById(memberId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            group.removeMember(user);
            groupRepository.save(group);
        } else {
            throw new RuntimeException("User not found");
        }
    }


}
