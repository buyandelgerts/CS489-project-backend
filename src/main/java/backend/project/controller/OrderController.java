package backend.project.controller;

import backend.project.dto.dto.*;
import backend.project.exception.*;
import backend.project.model.Order;
import backend.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) throws ProductNotFoundException {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody Order order, Authentication authentication) throws AlreadyExistException, OrderFailedException {
        return ResponseEntity.ok(orderService.saveOrder(order, authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable long id, @RequestBody Order order) throws ProductNotFoundException {
        return ResponseEntity.ok(orderService.updateOrder(id, order));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) throws ProductNotFoundException {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
