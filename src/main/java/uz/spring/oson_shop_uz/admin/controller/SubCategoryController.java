package uz.spring.oson_shop_uz.admin.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.spring.oson_shop_uz.admin.entity.SubCategory;
import uz.spring.oson_shop_uz.admin.receive.CategoryDTO;
import uz.spring.oson_shop_uz.admin.receive.SubCategoryDTO;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.admin.service.SubCategoryService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/subcategory")
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @PostMapping
    public HttpEntity<?> addSubCategory(@Valid @RequestBody SubCategoryDTO subCategoryDTO) {
        ApiResponse addSubCategory = subCategoryService.add(subCategoryDTO);
        return ResponseEntity.status(addSubCategory.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(addSubCategory.getMessage());
    }


    @GetMapping
    public List<SubCategory> getSubCategoryList() {
        System.out.println(subCategoryService.get().size());
        return subCategoryService.get();
    }

}
