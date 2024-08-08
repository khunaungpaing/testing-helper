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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDate;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp lastUpdatedDate;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean archived;

    @ManyToOne
    @JoinColumn(name = "ownerID", referencedColumnName = "id")
    private User owner;
}
