package uz.spring.oson_shop_uz.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.spring.oson_shop_uz.entity.Person;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Person, Long> {

    boolean existsByUsername(String username);

    Optional<Person> findByUsername(String username);

}
