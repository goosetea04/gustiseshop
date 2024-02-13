package id.ac.ui.cs.advprog.eshop.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.*;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("CreateProduct", viewName);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        when(productService.create(product)).thenReturn(product);

        String viewName = productController.createProductPost(product, model);

        assertEquals("redirect:list", viewName);
        verify(productService, times(1)).create(product);
    }

    @Test
    void testProductListPage() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(products);

        String viewName = productController.productListPage(model);

        assertEquals("productList", viewName);
        verify(model, times(1)).addAttribute("products", products);
    }

    @Test
    void testEditProductPage() {
        String id = "1";
        Product product = new Product();
        when(productService.get(id)).thenReturn(product);

        String viewName = productController.editProductPage(model, id);

        assertEquals("EditProduct", viewName);
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testEditProductPost() {
        String id = "1";
        Product product = new Product();
        when(productService.edit(id, product)).thenReturn(product);

        String viewName = productController.editProductPost(product, model, id);

        assertEquals("redirect:/product/list", viewName);
        verify(productService, times(1)).edit(id, product);
    }

    @Test
    void testDeleteProduct() {
        String id = "1";
        when(productService.delete(id)).thenReturn(true);

        String viewName = productController.deleteProduct(id);

        assertEquals("redirect:/product/list", viewName);
        verify(productService, times(1)).delete(id);
    }
}

