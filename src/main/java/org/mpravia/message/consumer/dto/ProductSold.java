package org.mpravia.message.consumer.dto;


import lombok.Data;

@Data
public class ProductSold {
    private String productCode;
    private long quantity;
}