package backend.project.service;

import backend.project.dto.dto.*;
import backend.project.exception.*;
import backend.project.model.*;
import backend.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public List<OrderDTO> getAllOrders(){
        List<Order> products = orderRepository.findAll();
        return products.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }

    public Order getOrderById(long id) throws ProductNotFoundException {
        return orderRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Order " + id + " not found."));
    }

    public OrderResponse saveOrder(Order order, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);
        if (order.getOrderItems() != null)
            order.getOrderItems().forEach(item -> item.setOrder(order));
        if (order.getPayment() != null)
            order.getPayment().setOrder(order);
        Order savedOrder = orderRepository.save(order);
        if(savedOrder.getId() != null)
            return new OrderResponse(savedOrder.getId(), "Successfully ordered!");
        else
            throw new OrderFailedException("Order failed!");
    }

    public Order updateOrder(long id, Order order) throws ProductNotFoundException {
        Order oldOrder = getOrderById(id);
        if(oldOrder == null)
            throw new ProductNotFoundException("Order " + id + " not found.");
        if(order.getOrderDate() != null)
            oldOrder.setOrderDate(order.getOrderDate());
        if(order.getTotalAmount() > 0)
            oldOrder.setTotalAmount(order.getTotalAmount());
        if(order.getPayment() != null)
            oldOrder.setPayment(order.getPayment());
        if(order.getUser() != null)
            oldOrder.setUser(order.getUser());
        return orderRepository.save(oldOrder);
    }

    public void deleteOrder(long id) throws ProductNotFoundException {
        Order order = getOrderById(id);
        if(order == null)
            throw new ProductNotFoundException("Order " + id + " not found.");
        orderRepository.delete(order);
    }
}
