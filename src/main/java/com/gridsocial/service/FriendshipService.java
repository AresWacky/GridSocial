package com.gridsocial.service;

import com.gridsocial.model.Friendship;
import com.gridsocial.model.User;
import com.gridsocial.repository.FriendshipRepository;
import com.gridsocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendshipService(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean sendFriendRequest(Long requesterId, Long addresseeId) {
        if (requesterId.equals(addresseeId)) {
            // Users cannot send a friend request to themselves
            return false;
        }

        Optional<User> requester = userRepository.findById(requesterId);
        Optional<User> addressee = userRepository.findById(addresseeId);

        if (requester.isPresent() && addressee.isPresent()) {
            Friendship friendship = new Friendship();
            friendship.setRequester(requester.get());
            friendship.setAddressee(addressee.get());
            friendship.setStatus(Friendship.FriendshipStatus.PENDING);
            friendshipRepository.save(friendship);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean acceptFriendRequest(Long requestId) {
        Optional<Friendship> friendship = friendshipRepository.findById(requestId);
        if (friendship.isPresent() && friendship.get().getStatus() == Friendship.FriendshipStatus.PENDING) {
            friendship.get().setStatus(Friendship.FriendshipStatus.ACCEPTED);
            friendshipRepository.save(friendship.get());
            return true;
        }
        return false;
    }

    @Transactional
    public boolean rejectFriendRequest(Long requestId) {
        Optional<Friendship> friendship = friendshipRepository.findById(requestId);
        if (friendship.isPresent() && friendship.get().getStatus() == Friendship.FriendshipStatus.PENDING) {
            friendship.get().setStatus(Friendship.FriendshipStatus.REJECTED);
            friendshipRepository.save(friendship.get());
            return true;
        }
        return false;
    }

    public List<User> listFriends(Long userId) {
        List<Friendship> friendships = friendshipRepository.findByRequesterIdOrAddresseeId(userId, userId);
        return friendships.stream()
                .filter(f -> f.getStatus() == Friendship.FriendshipStatus.ACCEPTED)
                .flatMap(f -> Stream.of(f.getRequester(), f.getAddressee()))
                .distinct()
                .filter(user -> !user.getId().equals(userId))
                .collect(Collectors.toList());
    }
}
