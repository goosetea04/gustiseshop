package id.ac.ui.cs.advprog.eshop.service;

import enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment create(Payment payment) {
        if (paymentRepository.findById(payment.getId()) == null) {
            return paymentRepository.save(payment);
        }
        return null;
    }

    @Override
    public void update(String paymentId, String status) {
        if (!isPaymentValid(status)) {
            throw new IllegalArgumentException(status);
        }

        Payment payment = paymentRepository.findById(paymentId);
        if (payment != null) {
            payment.setStatus(status);
            paymentRepository.save(payment);
        } else {
            throw new IllegalArgumentException(paymentId);
        }
    }

    private boolean isPaymentValid(String status) {
        return status != null && (status.equals(OrderStatus.SUCCESS.getValue()) || status.equals(OrderStatus.REJECTED.getValue()));
    }

    @Override
    public Payment findById(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public String confirmVoucherCode(String voucherCode) {
        if (confirmVoucherNumberCode(voucherCode)) {
            return "SUCCESS";
        } else {
            return "REJECTED";
        }
    }

    private boolean confirmVoucherNumberCode(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            return false;
        }

        int digitCount = 0;
        for (int i = 5; i < voucherCode.length(); i++) {
            if (Character.isDigit(voucherCode.charAt(i))) {
                digitCount++;
            }
        }

        return digitCount >= 8;
    }

    public boolean ValidTransfer(Map<String, String> paymentData) {
        String CODAddress = paymentData.get("Address");
        String CODFee = paymentData.get("Fee");

        return CODAddress != null && !CODAddress.isEmpty() && CODFee != null && !CODFee.isEmpty();
    }




}