package backend.project.dto.dto;

import backend.project.model.*;

import java.time.LocalDate;

public class OrderDTO {
    private Long id;
    private LocalDate orderDate;
    private double totalAmount;

    public OrderDTO(Order order) {
      this.id = order.getId();
      this.orderDate = order.getOrderDate();
      this.totalAmount = order.getTotalAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
