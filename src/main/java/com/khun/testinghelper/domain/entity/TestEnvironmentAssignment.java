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
@Table(name = "TestEnvironmentAssignment")
public class TestEnvironmentAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assignment_seq")
    @SequenceGenerator(name = "assignment_seq", sequenceName = "assignment_seq", allocationSize = 1)
    private Long assignmentID;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "testCaseID", referencedColumnName = "id")
    private TestCase testCase;

    @ManyToOne
    @JoinColumn(name = "environmentID", referencedColumnName = "id")
    private Environment environment;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp assignedDate;

    private Timestamp completionDate;
}
