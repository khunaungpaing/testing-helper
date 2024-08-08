package com.khun.testinghelper.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProjectMemberId implements Serializable {
    private Long projectID;
    private Long userID;
}
