package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.List;

public interface PaymentService {
    Payment create(Payment payment);

    void update(String paymentId, String status);

    Payment findById(String paymentId);

    List<Payment> findAll();
}
