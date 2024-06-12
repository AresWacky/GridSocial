package com.gridsocial.model;

import jakarta.persistence.*;

@Entity
@Table(name = "friendship")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "id")
    private User requester;

    @ManyToOne
    @JoinColumn(name = "addressee_id", referencedColumnName = "id")
    private User addressee;

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    public Friendship(User requester, User addressee, FriendshipStatus status) {
        this.requester = requester;
        this.addressee = addressee;
        this.status = status;
    }


    public void setRequester(User user) {
        this.requester = user;
    }

    public void setAddressee(User user) {
        this.addressee = user;
    }

    public void setStatus(FriendshipStatus friendshipStatus) {
        this.status = friendshipStatus;
    }

    public Object getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getRequester() {
        return requester;
    }

    public User getAddressee() {
        return addressee;
    }

    public enum FriendshipStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    public Friendship() {
    }


}



