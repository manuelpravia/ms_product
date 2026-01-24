package org.mpravia.dto;

import lombok.Data;

@Data
public class ProductRequestDto {

    private String code;
    private String name;
    private String description;
    private String presentation;
    private double price;
    private Integer stock;
    private Long categoryId;
    private  String imageUrl;

}
