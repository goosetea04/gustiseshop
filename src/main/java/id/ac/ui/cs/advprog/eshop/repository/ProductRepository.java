package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository
public class ProductRepository implements RepositoryBase<Product> {
    private List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    @Override
    public Product delete(String productId) {
        Iterator<Product> iterator = productData.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId().equals(productId)) {
                iterator.remove();
                return product;
            }
        }
        return null; // Product not found
    }

    public Product replace(int index, Product product) {
        if (index >= 0 && index < productData.size()) {
            return productData.set(index, product);
        }
        return null; // Index out of bounds
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

    @Override
    public List<Product> findAll() {
        return productData;
    }
    @Override
    public Product update(String id, Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(id)) {
                productData.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null; // Product not found
    }
    @Override
    public Product findById(String id) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null; // Product not found
    }


}
