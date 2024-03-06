package id.ac.ui.cs.advprog.eshop.repository;
import enums.OrderStatus;
import enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import  static  org.junit.jupiter.api.Assertions.*;
public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put(PaymentMethod.VOUCHER.getValue(), "ESHOP12345678CAB");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("29f8db42-2c60-41ca-a8fd-abc8bfd947f1", PaymentMethod.VOUCHER.getValue(), paymentData, OrderStatus.SUCCESS.getValue());
        payments.add(payment1);
    }
    @Test
    void testFindByIdIfIdFound() {
        for(Payment payment: payments){
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payments.get(0).getId(), findResult.getId());
        assertEquals(payments.get(0).getMethod(), findResult.getMethod());
        assertEquals(payments.get(0).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(0).getStatus(), findResult.getStatus());
    }
    @Test
    void testSaveUpdate() {
        Payment payment1 = payments.get(0);
        paymentRepository.save(payment1);
        Payment newPayment = new Payment(payment1.getId(), payment1.getMethod(), payment1.getPaymentData(), payment1.getStatus());
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payment1.getId(), result.getId());
        assertEquals(payment1.getId(), findResult.getId());
        assertEquals(payment1.getMethod(), findResult.getMethod());
        assertEquals(payment1.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment1.getStatus(), findResult.getStatus());
    }
    @Test
    void testSaveCreate() {
        Payment payment1 = payments.get(0);
        Payment result = paymentRepository.save(payment1);

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payment1.getId(), result.getId());
        assertEquals(payment1.getId(), findResult.getId());
        assertEquals(payment1.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment1.getMethod(), findResult.getMethod());
        assertEquals(payment1.getStatus(), findResult.getStatus());
    }
    @Test
    void testFindByIdIfIdNotFound() {
        for(Payment payment: payments){
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("Ga ada Id ka");
        assertNull(findResult);
    }
    @Test
    void testFindAll() {
        for(Payment payment: payments){
            paymentRepository.save(payment);
        }

        List<Payment> paymentList = paymentRepository.findAll();
        assertEquals(1, paymentList.size());
    }
}
