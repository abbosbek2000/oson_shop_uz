package uz.spring.oson_shop_uz.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.spring.oson_shop_uz.admin.entity.Category;
import uz.spring.oson_shop_uz.admin.receive.CategoryDTO;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.admin.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(
            CategoryService categoryService
    ) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> listCategory() {
        List<Category> categories = categoryService.get();
        return categories;
    }

    @PostMapping
    public HttpEntity<?> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        System.out.println(categoryDTO.getCategoryName());
        ApiResponse addedCategory = categoryService.add(categoryDTO);
        return ResponseEntity.status(addedCategory.isSuccess()
                ? 201 : 409).body(addedCategory.getMessage());
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editCategory(@RequestBody CategoryDTO categoryDTO,
                                      @PathVariable Long id) {
        ApiResponse apiResponse = categoryService.edit(categoryDTO, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable(value = "id") Long id) {
        ApiResponse apiResponse = categoryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
