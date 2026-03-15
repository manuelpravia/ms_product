package org.mpravia.message.consumer.dto;



import lombok.Data;

import java.util.List;

@Data
public class OrderCreatedEvent {
    private String orderId;
    private List<ProductSold> products;
}
