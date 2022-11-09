package uz.spring.oson_shop_uz.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.dto.PaymentDTO;
import uz.spring.oson_shop_uz.entity.Payment;
import uz.spring.oson_shop_uz.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping
    public HttpEntity<?> buyProduct(@RequestBody PaymentDTO paymentDTO) {
        ApiResponse apiResponse = paymentService.buyProduct(paymentDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409)
                .body(apiResponse);
    }

    @GetMapping
    public List<Payment> list() {
        return paymentService.list();
    }

}
