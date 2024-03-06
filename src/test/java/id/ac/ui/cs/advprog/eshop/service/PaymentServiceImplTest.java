package id.ac.ui.cs.advprog.eshop.service;

import enums.*;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;

import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments;
    private Payment ;

    @BeforeEach
    void setUp() {
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("Voucher", "ESHOP12345678CAB");
        Map<String, String> CODData = new HashMap<>();
        CODData.put("Address", "Jl. Kabel No.502");
        CODData.put("Fee", "200.000");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("15a149b7-79b8-42c2-b4b0-5da093b5af4c", "Voucher", paymentData, "SUCCESS");
        payments.add(payment1);
        Payment payment2 = new Payment("02e082ac-fbe2-4a2b-946d-ef2b62c9c176", "Cash On Delivery", CODData, "SUCCESS");
        payments.add(payment2);
    }

    @Test
    void createPaymentTest() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.create(payment);
        doReturn(payment).when(paymentRepository).save(payment);
        Payment result1 = paymentService.create(payment);
        verify(paymentRepository, times(2)).save(payment);
        assertEquals(payment.getId(), result1.getId());
    }
    @Test
    void createPaymentIfAlreadyExistTest() {
        Payment payment1 = payments.get(0);
        doReturn(payment1).when(paymentRepository).findById(payment1.getId());
        assertNull(paymentService.create(payment1));
        verify(paymentRepository, times(0)).save(payment1);
    }
    @Test
    void updateStatusTest() {
        assertThrows(IllegalArgumentException.class, () -> paymentService.update("paymentId", "ga ada id ka"));
        verify(paymentRepository, never()).save(any(Payment.class));
    }
    @Test
    void findAllTest() {
        List<Payment> results = paymentService.findAll();
        assertTrue(results.isEmpty());
    }
    @Test
    void findByIdIfIdFound() {
        Payment  payment1= payments.get(0);
        doReturn(payment1).when(paymentRepository).findById(payment1.getId());
        Payment result = paymentService.findById(payment1.getId());
        assertEquals(payment1.getId(), result.getId());
    }
    @Test
    void findByIdIfIdNotFound() {
        doReturn(null).when(paymentRepository).findById("Meow");
        assertNull(paymentService.findById("Meow"));
    }
    @Test
    void updateStatusInvalidPaymentIdTest() {
        doReturn(null).when(paymentRepository).findById("zczc");

        assertThrows(IllegalArgumentException.class, () -> paymentService.update("zczc", "SUCCESS"));
    }
    @Test
    void updateStatusWhenPaymentExistsTest() {

        String paymentId = "ddfdecbc-5947-47bd-8326-35545d43a620";
        String status = "SUCCESS";
        Payment payment = new Payment(paymentId, "Voucher", null, null);
        doReturn(payment).when(paymentRepository).findById(paymentId);


        paymentService.update(paymentId, status);


        assertEquals(status, payment.getStatus());
        verify(paymentRepository, times(1)).savePayment(payment);
    }
    @Test
    void testUpdateStatusWhenPaymentNotFound() {

        String paymentId = "ddfdecbc-5947-47bd-8326-35545d43a620";
        String status = "SUCCESS";
        doReturn(null).when(paymentRepository).findById(paymentId);


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.update(paymentId, status);
        });

        assertEquals(paymentId, exception.getMessage());
        verify(paymentRepository, never()).savePayment(any());
    }
    // ----- Tests for Voucher Code -----

    @Test
    void testValidateVoucherCodeWithValidCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("Voucher", "ESHOP12345678CAB");
        Payment payment = new Payment("ddfdecbc-5947-47bd-8326-35545d494de8", "Voucher", paymentData, "SUCCESS");

        String status = paymentService.confirmVoucherCode(payment.getPaymentData().get("Voucher"));

        assertEquals("SUCCESS", status);
    }
    @Test
    void testValidateVoucherCodeWithInvalidCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("Voucher", "ga ada voucher ka");
        Payment payment = new Payment("2b420113-bd1b-4b18-9fb2-72e8221aaf43", "Voucher", paymentData, "SUCCESS");

        String status = paymentService.confirmVoucherCode(payment.getPaymentData().get("Voucher"));

        assertEquals("REJECTED", status);
    }
    @Test
    void testValidateVoucherCodeWithInvalidCodeNumber() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("Voucher", "ESHOP123456EDCAB");
        Payment payment = new Payment("2b420113-bd1b-4b18-9fb2-72e8221aaf53", "Voucher", paymentData, "SUCCESS");

        String status = paymentService.confirmVoucherCode(payment.getPaymentData().get("Voucher"));

        assertEquals("REJECTED", status);
    }
    // ----- Tests for COD -----
    @Test
    void validCODPaymentTest() {
        Map<String, String> CODData = new HashMap<>();
        CODData.put("Address", "Jl. Bali No.503");
        CODData.put("Fee","20.000");
        boolean isThere = paymentService.ValidTransfer(CODData);
        assertTrue(isThere);

    }
}
