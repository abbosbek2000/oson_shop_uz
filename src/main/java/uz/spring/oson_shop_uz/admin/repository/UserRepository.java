package uz.spring.oson_shop_uz.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.spring.oson_shop_uz.admin.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}
