package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Payment {
    private String id;
    private String method;
    private Map<String, String> paymentData;
    @Setter
    private String status;

    public Payment(String id, String method, Map<String, String> paymentData, String status){
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.status = status;
    }

    public String getId() {
        return id;
    }
    public String getMethod() {
        return method;
    }
    public String getStatus() {
        return status;
    }
    public Map<String, String> getPaymentData() {
        return paymentData;
    }
}
