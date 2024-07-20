package org.aadi.bookstore.orders.web.controllers;

import org.aadi.bookstore.orders.domain.OrderNotFoundException;
import org.aadi.bookstore.orders.domain.OrderService;
import org.aadi.bookstore.orders.domain.models.CreateOrderRequest;
import org.aadi.bookstore.orders.domain.models.CreateOrderResponse;
import org.aadi.bookstore.orders.domain.models.OrderDTO;
import org.aadi.bookstore.orders.domain.models.OrderSummary;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping
    List<OrderSummary> getOrders() {
        return orderService.findOrders();
    }

    @GetMapping(value = "/{orderNumber}")
    OrderDTO getOrder(@PathVariable(value = "orderNumber") String orderNumber) {
        log.info("Fetching order by orderNumber: {}", orderNumber);
        return orderService.findOrder(orderNumber)
                .orElseThrow(() -> OrderNotFoundException.forOrderNumber(orderNumber));
    }
}
