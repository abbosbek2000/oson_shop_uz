package uz.spring.oson_shop_uz.admin.receive;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDTO {
    private String productName;

    private double price;

    private Long subCategoryId;
}
