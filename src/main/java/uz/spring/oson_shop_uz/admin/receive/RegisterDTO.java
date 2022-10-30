package uz.spring.oson_shop_uz.admin.receive;
import lombok.Data;
import javax.validation.constraints.NotNull;
@Data
public class RegisterDTO {
    @NotNull(message = "firstName bosh bo'lmasligi kerak")
    private String firstName;

    @NotNull(message = "lastName bosh bo'lmasligi kerak")
    private String lastName;

    @NotNull(message = "username bosh bo'lmasligi kerak")
    private String username;

    @NotNull(message = "password  bosh bo'lmasligi kerak")
    private String password;


    @NotNull(message = "Parol takror bosh bo'lmasligi kerak")
    private String prePassword;

}
