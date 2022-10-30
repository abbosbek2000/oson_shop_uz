package uz.spring.oson_shop_uz.admin.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.spring.oson_shop_uz.admin.entity.Category;
import uz.spring.oson_shop_uz.admin.exception.CustomException;
import uz.spring.oson_shop_uz.admin.receive.CategoryDTO;
import uz.spring.oson_shop_uz.admin.repository.CategoryRepository;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.admin.security.base.BaseService;
import java.util.List;
@Service
public class CategoryService implements BaseService<CategoryDTO, ApiResponse,Category> {
    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryService(
            CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

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

    @Override
    public ApiResponse edit(CategoryDTO categoryDTO, Long aLong) {
        return null;
    }

    @Override
    public ApiResponse delete(Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> new CustomException("this id category not found " + id));
        categoryRepository.delete(category);
        return new ApiResponse("succsessfully deleted", true);
    }
}
