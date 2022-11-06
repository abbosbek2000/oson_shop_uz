package uz.spring.oson_shop_uz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.spring.oson_shop_uz.entity.base.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shopping extends AbsEntity {
    private Integer count;

    private String checkId;

    private boolean status;

    private Long productId;

    @OneToMany
    private List<Product> products;

}
