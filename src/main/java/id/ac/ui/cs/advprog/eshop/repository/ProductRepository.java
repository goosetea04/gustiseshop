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

    public Product edit(Product editedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product existingProduct = productData.get(i);
            if (existingProduct.getProductId().equals(editedProduct.getProductId())) {
                productData.set(i, editedProduct);
                return editedProduct;
            }
        }
        return null; // Product not found
    }

    public boolean delete(String productId) {
        Iterator<Product> iterator = productData.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId().equals(productId)) {
                iterator.remove();
                return true; // Product deleted
            }
        }
        return false; // Product not found
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
