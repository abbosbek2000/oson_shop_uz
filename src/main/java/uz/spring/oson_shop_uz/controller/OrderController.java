package uz.spring.oson_shop_uz.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.dto.OrderDTO;
import uz.spring.oson_shop_uz.entity.Order;
import uz.spring.oson_shop_uz.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public HttpEntity<?> selectOrderProduct(@RequestBody OrderDTO orderDTO) {
        ApiResponse apiResponse = orderService.shopProductBasket(orderDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409)
                .body(apiResponse);
    }

    @GetMapping
    public List<Order> list() {
        return orderService.getOrderList();
    }
}
