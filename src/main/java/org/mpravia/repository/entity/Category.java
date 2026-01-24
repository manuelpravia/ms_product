package org.mpravia.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    @Column(name = "create_date", nullable = false, updatable = false)
    private OffsetDateTime createDate;

    @Column(name = "change_date", nullable = false)
    private OffsetDateTime changeDate;

    @PrePersist
    protected void onCreate() {
        createDate = OffsetDateTime.now();
        changeDate = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        changeDate = OffsetDateTime.now();
    }
}
