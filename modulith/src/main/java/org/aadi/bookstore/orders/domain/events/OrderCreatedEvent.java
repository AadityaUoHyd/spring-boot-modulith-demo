package org.aadi.bookstore.orders.domain.events;

public record OrderCreatedEvent(
        String orderNumber,
        String productCode,
        int quantity) {
}
