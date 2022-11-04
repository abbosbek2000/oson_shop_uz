package uz.spring.oson_shop_uz.admin.receive;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class CategoryDTO {
    @NotNull(message = "This is not empty")
    private String categoryName;
}
