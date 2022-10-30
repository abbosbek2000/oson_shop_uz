package uz.spring.oson_shop_uz.admin.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.spring.oson_shop_uz.admin.entity.base.AbsEntity;
import uz.spring.oson_shop_uz.admin.entity.enums.RoleName;

import javax.persistence.*;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbsEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    private RoleName roleEnum;

    @Override
    public String getAuthority() {
        return this.roleEnum.name();
    }

}
