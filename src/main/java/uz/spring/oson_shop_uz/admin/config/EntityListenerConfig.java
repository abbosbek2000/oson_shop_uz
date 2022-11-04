package uz.spring.oson_shop_uz.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.spring.oson_shop_uz.admin.entity.User;

import java.util.UUID;

@Configuration
@EnableJpaAuditing
public class EntityListenerConfig {
    @Bean
    AuditorAware<String> auditorAware() {
        return new EntityListener();
    }
}
