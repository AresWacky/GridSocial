package com.gridsocial.controller;

import com.gridsocial.model.User;
import com.gridsocial.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendshipController {

    private final FriendshipService friendshipService;

    @Autowired
    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    // Send a friend request
    @PostMapping("/request")
    public ResponseEntity<String> sendFriendRequest(@RequestParam Long requesterId, @RequestParam Long addresseeId) {
        boolean sent = friendshipService.sendFriendRequest(requesterId, addresseeId);
        if (sent) {
            return ResponseEntity.ok("Friend request sent successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to send friend request.");
        }
    }

    // Accept a friend request
    @PostMapping("/accept")
    public ResponseEntity<String> acceptFriendRequest(@RequestParam Long requestId) {
        boolean accepted = friendshipService.acceptFriendRequest(requestId);
        if (accepted) {
            return ResponseEntity.ok("Friend request accepted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to accept friend request.");
        }
    }

    // Reject a friend request
    @PostMapping("/reject")
    public ResponseEntity<String> rejectFriendRequest(@RequestParam Long requestId) {
        boolean rejected = friendshipService.rejectFriendRequest(requestId);
        if (rejected) {
            return ResponseEntity.ok("Friend request rejected successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to reject friend request.");
        }
    }

    // List friends of a user
    @GetMapping("/{userId}/list")
    public ResponseEntity<List<User>> listFriends(@PathVariable Long userId) {
        List<User> friends = friendshipService.listFriends(userId);
        return ResponseEntity.ok(friends);
    }
}

