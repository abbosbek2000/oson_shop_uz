package uz.spring.oson_shop_uz.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.spring.oson_shop_uz.admin.entity.Product;
import uz.spring.oson_shop_uz.admin.receive.ProductDTO;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.admin.service.AttachmentService;
import uz.spring.oson_shop_uz.admin.service.ProductService;

import java.io.IOException;
import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private static String UPLOADED_FOLDER = "yuklamganlar";
    private final ProductService productService;
    private final AttachmentService attachmentService;

    @Autowired
    public ProductController(ProductService productService, AttachmentService attachmentService) {
        this.productService = productService;
        this.attachmentService = attachmentService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpEntity<?> upload(
            @RequestPart("productName") String productName,
            @RequestPart("price") String price,
            @RequestPart("subCategoryId") String subCategoryId,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(productName);
        productDTO.setPrice(Double.parseDouble(price));
        productDTO.setSubCategoryId(Long.parseLong(subCategoryId));
        ApiResponse apiResponse = productService.add(productDTO, file);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping
    public List<Product> getListProduct() {
        return productService.get();
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> ediProduct(@RequestBody ProductDTO productDTO,
                                    @PathVariable(value = "id") Long id) {
        ApiResponse apiResponse = productService.edit(productDTO, id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                        HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> ediProduct(@PathVariable(value = "id") Long id) {
        ApiResponse apiResponse = productService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);

    }
}
