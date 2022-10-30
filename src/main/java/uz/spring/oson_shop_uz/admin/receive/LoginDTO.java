package uz.spring.oson_shop_uz.admin.receive;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {

    @NotNull(message = "username bosh bo'lmasligi kerak")
    private String username;

    @NotNull(message = "password  bosh bo'lmasligi kerak")
    private String password;

}
