package uz.spring.oson_shop_uz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.spring.oson_shop_uz.entity.base.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment extends AbsEntity {
    private Long userId;

    private Long productId;

    private String checkId;

    private boolean status;

    private Double amount;

    @ManyToOne
    private Person person;


}
