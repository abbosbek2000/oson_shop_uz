package uz.spring.oson_shop_uz.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.spring.oson_shop_uz.admin.entity.Category;
import uz.spring.oson_shop_uz.admin.entity.SubCategory;
import uz.spring.oson_shop_uz.admin.receive.SubCategoryDTO;
import uz.spring.oson_shop_uz.admin.repository.CategoryRepository;
import uz.spring.oson_shop_uz.admin.repository.SubCategoryRepository;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.admin.security.base.BaseService;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService implements BaseService<SubCategoryDTO, ApiResponse, SubCategory> {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Autowired
    public SubCategoryService(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public ApiResponse add(SubCategoryDTO subCategoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(subCategoryDTO.getCategoryId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            SubCategory subCategory = new SubCategory();
            subCategory.setName(subCategoryDTO.getSubCategoryName());
            subCategory.setCategory(category);
            subCategoryRepository.save(subCategory);
            return new ApiResponse("SUCCESSFULLY ADDED SUBCATEGORY", true);
        }
        return new ApiResponse("THIS CATEGORY ID NOT FOUND", false);
    }

    @Override
    public List<SubCategory> get() {
        return subCategoryRepository.findAll();
    }


    @Override
    public ApiResponse edit(SubCategoryDTO subCategoryDTO, Long aLong) {
        return null;
    }

    @Override
    public ApiResponse delete(Long id) {
        return null;
    }


}
