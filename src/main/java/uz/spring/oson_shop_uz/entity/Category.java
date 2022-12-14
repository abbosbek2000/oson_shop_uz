package uz.spring.oson_shop_uz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.spring.oson_shop_uz.entity.base.AbsEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Category extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;


    @JsonIgnore
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<SubCategory> subCategories;

}
