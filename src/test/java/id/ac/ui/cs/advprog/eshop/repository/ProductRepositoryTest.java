package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.List;

import  static  org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){}

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());

    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testEditProduct() {
        Product originalProduct = createSampleProduct();
        productRepository.create(originalProduct);

        Product editedProduct = new Product();
        editedProduct.setProductId(originalProduct.getProductId());
        editedProduct.setProductName("Sampo Cap Bumbu");
        editedProduct.setProductQuantity(50);

        Product result = productRepository.edit(editedProduct);

        assertNotNull(result);
        assertEquals(originalProduct.getProductId(), result.getProductId());
        assertEquals("Sampo Cap Bumbu", result.getProductName());
        assertEquals(50, result.getProductQuantity());
    }

    @Test
    void testEditNonExistingProduct() {
        Product editedProduct = new Product();
        editedProduct.setProductId("a1b2c3d4-e5f6-4a3b-8c9d-0e1f2a3b4c5d");
        editedProduct.setProductName("Sampo Cap Bango");
        editedProduct.setProductQuantity(50);

        Product result = productRepository.edit(editedProduct);

        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        Product product = createSampleProduct();
        productRepository.create(product);

        boolean result = productRepository.delete(product.getProductId());

        assertTrue(result);
        assertFalse(productRepository.findAll().hasNext());
    }

    @Test
    void testDeleteNonExistingProduct() {
        boolean result = productRepository.delete("a1b2c3d4-e5f6-4a3b-8c9d-0e1f2a3b4c5d");

        assertFalse(result);
    }


    private Product createSampleProduct() {
        Product product = new Product();
        product.setProductId("sample-id");
        product.setProductName("Sample Product");
        product.setProductQuantity(100);
        return product;
    }
}