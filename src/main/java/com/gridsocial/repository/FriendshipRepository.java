package com.gridsocial.repository;

import com.gridsocial.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByRequesterIdOrAddresseeId(Long requesterId, Long addresseeId);
}
