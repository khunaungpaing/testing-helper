package com.khun.testinghelper.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Environment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "environment_seq")
    @SequenceGenerator(name = "environment_seq", sequenceName = "environment_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String configuration;
}
