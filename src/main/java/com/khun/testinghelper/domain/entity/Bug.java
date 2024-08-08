package com.khun.testinghelper.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bug_seq")
    @SequenceGenerator(name = "bug_seq", sequenceName = "bug_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "testCaseID", referencedColumnName = "id")
    private TestCase testCase;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String stepsToReproduce;

    @Column(nullable = false)
    private String severity;

    @Column(nullable = false)
    private String priority;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDate;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp modifiedDate;
}
