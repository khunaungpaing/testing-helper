package com.khun.testinghelper.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"tag"})
@Entity
public class BugTag {
    @EmbeddedId
    private BugTagId id;

    @ManyToOne
    @MapsId("bugID")
    @JoinColumn(name = "bugID", referencedColumnName = "id")
    private Bug bug;

    @Column(nullable = false, insertable = false, updatable = false)
    private String tag;
}
