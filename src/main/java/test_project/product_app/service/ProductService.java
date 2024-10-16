package test_project.product_app.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import test_project.product_app.model.Product;
import test_project.product_app.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProduct (Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public Product addProduct (Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct (Long id, Product product) {
        Product currentProduct = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

        currentProduct.setName(product.getName());
        currentProduct.setType(product.getType());
        currentProduct.setPrice(product.getPrice());

        return productRepository.save(currentProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

}
