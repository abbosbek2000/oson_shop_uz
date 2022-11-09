package uz.spring.oson_shop_uz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.spring.oson_shop_uz.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
