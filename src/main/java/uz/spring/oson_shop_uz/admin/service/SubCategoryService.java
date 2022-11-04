package uz.spring.oson_shop_uz.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.spring.oson_shop_uz.admin.entity.Category;
import uz.spring.oson_shop_uz.admin.entity.SubCategory;
import uz.spring.oson_shop_uz.admin.exception.CustomException;
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
    public ApiResponse edit(SubCategoryDTO subCategoryDTO, Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(subCategoryDTO.getCategoryId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
            if (optionalSubCategory.isPresent()) {
                SubCategory subCategory = optionalSubCategory.get();
                subCategory.setCategory(category);
                subCategory.setName(subCategoryDTO.getSubCategoryName());
                subCategoryRepository.save(subCategory);
                return new ApiResponse("SUCCESSFULLY EDITED SUBCATEGORY", true);
            }
            return new ApiResponse("THIS CAN NOT FIND SUB CATEGORY", false);
        }

        return new ApiResponse("THIS CAN NOT FIND CATEGORY", false);
    }

    @Override
    public ApiResponse delete(Long id) {
        try {
            Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
            subCategoryRepository.delete(optionalSubCategory.get());
            return new ApiResponse("successfully deleted", true);
        } catch (CustomException e) {
            throw new CustomException("this id category not found " + id);
        }


    }
}
