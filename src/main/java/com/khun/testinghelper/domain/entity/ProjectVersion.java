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
public class ProjectVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "version_seq")
    @SequenceGenerator(name = "version_seq", sequenceName = "version_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projectID", referencedColumnName = "id")
    private Project project;

    @Column(nullable = false)
    private String versionName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Timestamp releaseDate;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean archived;

    @Column(columnDefinition = "TEXT")
    private String releaseNotes;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDate;
}
