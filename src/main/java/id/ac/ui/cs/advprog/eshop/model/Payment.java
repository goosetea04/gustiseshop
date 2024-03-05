package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
}
