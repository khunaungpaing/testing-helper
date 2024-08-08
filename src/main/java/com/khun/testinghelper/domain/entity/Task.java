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
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
    private Long taskID;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "checklistID", referencedColumnName = "id")
    private Checklist checklist;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "assigneeID", referencedColumnName = "id")
    private User assignee;

    private Timestamp dueDate;

    @Column(nullable = false)
    private String priority;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDate;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp modifiedDate;
}
