package com.gridsocial.repository;
import com.gridsocial.model.GroupRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRequestRepository extends JpaRepository<GroupRequest, Long> {
    // Additional query methods if needed
}
