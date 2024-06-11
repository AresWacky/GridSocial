package com.gridsocial.model;



import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "group_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;
    private String description;
    private String status; // 'pending' or 'active'
    private Date date;

    @PrePersist
    protected void onCreate() {
        this.date = new Date();
    }

}