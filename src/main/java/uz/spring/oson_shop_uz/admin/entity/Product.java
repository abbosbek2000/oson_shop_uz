package uz.spring.oson_shop_uz.admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.spring.oson_shop_uz.admin.entity.base.AbsEntity;

import javax.persistence.*;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "sub_category_id"}))
@EntityListeners(AuditingEntityListener.class)
public class Product extends AbsEntity {
    @Column(unique = true, nullable = false)
    private String name;

    private double price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SubCategory subCategory;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Attachment attachments;

}
