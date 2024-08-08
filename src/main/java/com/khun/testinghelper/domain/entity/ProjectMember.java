package com.khun.testinghelper.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"projectID", "userID"})
@Entity
@Table(name = "ProjectMember")
public class ProjectMember {
    @EmbeddedId
    private ProjectMemberId id;

    @ManyToOne
    @MapsId("projectID")
    @JoinColumn(name = "projectID", referencedColumnName = "id")
    private Project project;

    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "userID", referencedColumnName = "id")
    private User user;
}
