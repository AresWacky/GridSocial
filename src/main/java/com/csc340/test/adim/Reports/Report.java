package com.csc340.test.adim.Reports;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportedBy;

    private String targetType; // Can be "PERSON", "GROUP", or "COMMENT"

    private Long targetId;

    private String reason;

    private LocalDate Date;

    private String action;

    // Getters and setters
}

