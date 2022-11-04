package uz.spring.oson_shop_uz.admin.service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.spring.oson_shop_uz.admin.entity.Category;
import uz.spring.oson_shop_uz.admin.exception.CustomException;
import uz.spring.oson_shop_uz.admin.receive.CategoryDTO;
import uz.spring.oson_shop_uz.admin.repository.CategoryRepository;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.admin.security.base.BaseService;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements BaseService<CategoryDTO, ApiResponse,Category> {
    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse add(CategoryDTO categoryDTO) {
        if (categoryDTO.getCategoryName().length() == 0)
            return new ApiResponse("this field is not empty", false);
        boolean isExistCategory = categoryRepository.existsByName(categoryDTO.getCategoryName());
        if (isExistCategory)
            return new ApiResponse("this is category field already exist", false);
        Category category = new Category();
        category.setName(categoryDTO.getCategoryName());
        categoryRepository.save(category);
        return new ApiResponse("Successfully added", true);
    }

    @Override
    public List<Category> get() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }
    @Override
    public ApiResponse edit(
            CategoryDTO categoryDTO, Long newProductID
    ) {
        Optional<Category> optionalCategory = categoryRepository.findById(newProductID);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDTO.getCategoryName());
            categoryRepository.save(category);
            return new ApiResponse("SUCCESSFULLY EDITED", true);
        }
        return new ApiResponse("this category didn't change", false);
    }

    @Override
    public ApiResponse delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("this id category not found " + id));
        categoryRepository.delete(category);
        return new ApiResponse("succsessfully deleted", true);
    }
}
