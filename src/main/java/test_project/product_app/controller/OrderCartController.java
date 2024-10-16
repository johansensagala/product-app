package test_project.product_app.controller;

import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test_project.product_app.model.OrderCart;
import test_project.product_app.model.OrderItem;
import test_project.product_app.model.Product;
import test_project.product_app.service.OrderCartService;

@RestController

@RequestMapping("api/order-carts")
public class OrderCartController {
    @Autowired
    private OrderCartService orderCartService;

    @PostMapping
    public OrderCart createCart() {
        return orderCartService.createCart();
    }

    @PostMapping("/{id}")
    public OrderItem addOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        return orderCartService.addOrderItem(id, orderItem);
    }

    @DeleteMapping("/{orderItemId}")
    public void deleteOrderItem (@PathVariable Long orderItemId) {
        orderCartService.deleteOrderItem(orderItemId);
    }

    @GetMapping("/{id}")
    public Page<OrderItem> getAllCartItems(@PathVariable Long id, Pageable pageable) {
        return orderCartService.getAllCartItems(id, pageable);
    }

    @GetMapping("/{id}/total-price")
    public ResponseEntity<Double> getTotalCartPrice(@PathVariable Long id) {
        Double totalPrice = orderCartService.calculateTotalPrice(id);
        return ResponseEntity.ok(totalPrice);
    }
}
