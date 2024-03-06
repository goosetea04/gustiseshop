package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.*;
public class PaymentRepository {
    private final Map<String, Payment> paymentMap;

    public PaymentRepository() {
        this.paymentMap = new HashMap<>();
    }
    public Payment save(Payment payment) {
        paymentMap.put(payment.getId(), payment);
        return payment;
    }

    public Payment findById(String id) {
        return paymentMap.get(id);
    }

    public List<Payment> findAll() {
        return new ArrayList<>(paymentMap.values());
    }
}
