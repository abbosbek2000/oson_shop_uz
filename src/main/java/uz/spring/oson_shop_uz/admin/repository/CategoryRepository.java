package uz.spring.oson_shop_uz.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.spring.oson_shop_uz.admin.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);

}
