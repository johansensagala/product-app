package test_project.product_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test_project.product_app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
