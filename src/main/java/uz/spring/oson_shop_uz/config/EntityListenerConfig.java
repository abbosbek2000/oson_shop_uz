package uz.spring.oson_shop_uz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class EntityListenerConfig {
    @Bean
    AuditorAware<String> auditorAware() {
        return new EntityListener();
    }
}
