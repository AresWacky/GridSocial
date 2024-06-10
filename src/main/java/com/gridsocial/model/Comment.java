package com.gridsocial.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;

    private String content;

    @ManyToOne
    private Feed feedId; // ID of the feed this comment is associated with
    // other fields as needed

}