package uz.spring.oson_shop_uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.spring.oson_shop_uz.admin.repository.ProductRepository;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.dto.PaymentDTO;
import uz.spring.oson_shop_uz.entity.Payment;
import uz.spring.oson_shop_uz.entity.Product;
import uz.spring.oson_shop_uz.entity.Person;
import uz.spring.oson_shop_uz.repo.PaymentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    public ApiResponse buyProduct(PaymentDTO paymentDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person currentUser = (Person) authentication.getPrincipal();
        if (currentUser != null) {
            Optional<Product> optionalProduct = productRepository.findById(paymentDTO.getProductId());
            if (optionalProduct.isPresent()) {
                Payment payment = new Payment();
                Product product = optionalProduct.get();
                payment.setProductId(product.getId());
                payment.setUserId(currentUser.getId());
                payment.setAmount(product.getPrice() * paymentDTO.getCountProduct());
                payment.setCheckId(UUID.randomUUID().toString());
                payment.setStatus(true);
                paymentRepository.save(payment);
                return new ApiResponse("SUCCESSFULLY BUY PRODUCT", true);
            }
            return new ApiResponse("this is not product", false);
        }
        return new ApiResponse("you did not registred yet", false);
    }

    public List<Payment> list() {
        return paymentRepository.findAll();
    }

}
