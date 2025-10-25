package backend.project.service;

import backend.project.exception.*;
import backend.project.model.Payment;
import backend.project.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(long id) throws ProductNotFoundException {
        return paymentRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Payment " + id + " Not Found"));
    }

    public Payment savePayment(Payment payment) throws IllegalArgumentException {
        if(payment.getAmount() <= 0)
            throw new IllegalArgumentException("Invalid payment amount!");
        return paymentRepository.save(payment);
    }

    public boolean isPaymentAmountValid(double amount) {
        return amount > 0;
    }
}
