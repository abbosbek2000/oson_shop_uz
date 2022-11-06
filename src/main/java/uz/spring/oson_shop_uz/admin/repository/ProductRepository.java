package uz.spring.oson_shop_uz.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.spring.oson_shop_uz.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
