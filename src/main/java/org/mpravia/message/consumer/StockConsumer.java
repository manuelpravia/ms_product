package org.mpravia.message.consumer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.mpravia.message.consumer.dto.OrderCreatedEvent;
import org.mpravia.service.ProductService;

@ApplicationScoped
public class StockConsumer {

    @Inject
    ProductService productService;

    @Incoming("orders")
    public void consume(OrderCreatedEvent event) {
        System.out.println("Event here =) ... ");

        System.out.println("Event: " + event.getOrderId());

        productService.updateStockEvent(event.getProducts());

    }

}