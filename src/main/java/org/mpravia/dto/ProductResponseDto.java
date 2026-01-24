package org.mpravia.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ProductResponseDto {
    private long id;
    private String code;
    private String name;
    private String description;
    private String presentation;
    private double price;
    private Integer stock;
    private Long categoryId;
    private  String imageUrl;
    private OffsetDateTime createDate;
    private OffsetDateTime changeDate;
}
