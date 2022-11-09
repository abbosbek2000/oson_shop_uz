package uz.spring.oson_shop_uz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.spring.oson_shop_uz.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
