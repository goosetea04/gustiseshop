package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
public interface ProductService {
    public Product create(Product product);
    public boolean delete(String id);

    public Product edit(String id, Product product);

    public Product get(String id);
    public List<Product> findAll();
}
