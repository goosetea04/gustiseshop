package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
public interface ProductService {
    Product create(Product product);
    Product delete(String id);

    Product edit(String id, Product product);

    Product get(String id);
    List<Product> findAll();
}
