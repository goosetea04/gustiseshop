package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }
    public boolean delete(Product product) {
        return productData.remove(product);
    }

    public Product replace(int index, Product product) {
        return productData.set(index, product);
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
