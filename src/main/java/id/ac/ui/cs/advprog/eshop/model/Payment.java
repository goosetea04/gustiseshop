package id.ac.ui.cs.advprog.eshop.model;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
}
