package com.gridsocial.model;

import com.gridsocial.service.UserService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


//    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<User> members = new HashSet<>();
//
//
//
//    public void addMember(User user) {
//        this.members.add(user);
//    }
//
//    public void removeMember(User user) {
//        this.members.remove(user);
//    }
//
//    public void removeMember(Long userId) {
//        this.members.removeIf(user -> user.getId().equals(userId));
//    }
}

