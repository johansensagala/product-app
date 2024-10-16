package test_project.product_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test_project.product_app.model.OrderCart;

public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {

}
