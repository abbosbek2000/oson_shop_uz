package uz.spring.oson_shop_uz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.spring.oson_shop_uz.admin.repository.ProductRepository;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.dto.OrderDTO;
import uz.spring.oson_shop_uz.entity.Order;
import uz.spring.oson_shop_uz.entity.Product;
import uz.spring.oson_shop_uz.repo.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public ApiResponse shopProductBasket(OrderDTO orderDTO) {
        Optional<Product> optionalProduct = productRepository.findById(orderDTO.getProductId());
        if (optionalProduct.isPresent()) {
            Order order = new Order();
            List<Order> list = new ArrayList<>();
            Product product = optionalProduct.get();
            if (orderDTO.getCount() == 0) {
                order.setAmount(product.getPrice());
                list.add(order);
                orderRepository.save(order);
            }
           else{
                order.setAmount(orderDTO.getCount() * product.getPrice());
                list.add(order);
                orderRepository.save(order);
           }
            return new ApiResponse("SUCCESSFULLY ORDERED", true);
        }
        return new ApiResponse("ERROR", false);
    }

    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

}
