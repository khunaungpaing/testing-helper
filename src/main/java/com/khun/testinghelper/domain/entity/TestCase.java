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
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "testcase_seq")
    @SequenceGenerator(name = "testcase_seq", sequenceName = "testcase_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "checklistID", referencedColumnName = "id")
    private Checklist checklist;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String steps;

    @Column(columnDefinition = "TEXT")
    private String expectedResult;

    @Column(columnDefinition = "TEXT")
    private String actualResult;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String priority;

    @Column(nullable = false)
    private String severity;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDate;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp modifiedDate;
}
