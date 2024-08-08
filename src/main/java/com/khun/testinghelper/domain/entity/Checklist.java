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
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checklist_seq")
    @SequenceGenerator(name = "checklist_seq", sequenceName = "checklist_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "versionID", referencedColumnName = "id")
    private ProjectVersion version;

    @Column(nullable = false)
    private String checklistName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDate;

    @Column(nullable = false)
    private String status;
}
