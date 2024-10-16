package test_project.product_app.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import test_project.product_app.model.OrderCart;
import test_project.product_app.model.OrderItem;
import test_project.product_app.model.Product;
import test_project.product_app.repository.OrderCartRepository;
import test_project.product_app.repository.OrderItemRepository;
import test_project.product_app.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderCartService {
    @Autowired
    private OrderCartRepository orderCartRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public OrderCart createCart() {
        OrderCart orderCart = new OrderCart();

        return orderCartRepository.save(orderCart);
    }

    public OrderItem addOrderItem(Long orderCartId, OrderItem orderItem) {
        OrderCart orderCart = orderCartRepository.findById(orderCartId)
                .orElseThrow(() -> new RuntimeException("Order cart not found."));

        Product product = productRepository.findById(orderItem.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found."));

        orderItem.setProduct(product);
        orderItem.setOrderCart(orderCart);

        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem (Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order Item not found");
        }

        orderItemRepository.deleteById(id);
    }

    public Page<OrderItem> getAllCartItems(Long orderCartId, Pageable pageable) {
        OrderCart orderCart = orderCartRepository.findById(orderCartId)
                .orElseThrow(() -> new RuntimeException("Order cart not found."));

        return orderItemRepository.findByOrderCart(orderCart, pageable);
    }

    public Double calculateTotalPrice(Long orderCartId) {
        OrderCart orderCart = orderCartRepository.findById(orderCartId)
                .orElseThrow(() -> new RuntimeException("Order cart not found."));

        List<OrderItem> orderItems = orderItemRepository.findByOrderCart(orderCart);

        return orderItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
