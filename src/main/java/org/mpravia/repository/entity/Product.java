package org.mpravia.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;


@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    private String name;
    private String description;
    private String presentation;
    private double price;
    private Integer stock;

    @Column(name = "categoryId")
    private Long categoryId;

    @Column(name = "image_url")
    private  String imageUrl;

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

