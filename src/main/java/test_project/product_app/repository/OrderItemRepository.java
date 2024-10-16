package test_project.product_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import test_project.product_app.model.OrderCart;
import test_project.product_app.model.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Page<OrderItem> findByOrderCart(OrderCart orderCart, Pageable pageable);
    List<OrderItem> findByOrderCart(OrderCart orderCart);
}
