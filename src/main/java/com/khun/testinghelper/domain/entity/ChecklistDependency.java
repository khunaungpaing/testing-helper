package com.khun.testinghelper.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"checklistID", "dependencyID"})
@Entity
public class ChecklistDependency {
    @EmbeddedId
    private ChecklistDependencyId id;

    @ManyToOne
    @MapsId("checklistID")
    @JoinColumn(name = "checklistID", referencedColumnName = "id")
    private Checklist checklist;

    @ManyToOne
    @MapsId("dependencyID")
    @JoinColumn(name = "dependencyID", referencedColumnName = "id")
    private Checklist dependency;
}
