package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product){
        product.setProductId(String.valueOf(UUID.randomUUID()));
        productRepository.create(product);
        return product;
    }
    @Override
    public Product delete(String id) {
        return productRepository.delete(id);
    }

    @Override
    public Product edit(String id, Product product) {
        Product existingProduct = productRepository.findById(id);
        if (existingProduct != null) {
            product.setProductId(existingProduct.getProductId());
            return productRepository.update(id, product);
        }
        return null;
    }

    @Override
    public Product get(String id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
