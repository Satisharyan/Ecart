package com.eccomerce.Ecart.Service;

import com.eccomerce.Ecart.Model.Category;
import com.eccomerce.Ecart.payload.CategoryDTO;
import com.eccomerce.Ecart.payload.ResponseCategory;

import java.util.List;

public interface CategoryService {
    ResponseCategory getAllCategories(Integer pageNumber,Integer pageSize,String SortBy,String SortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,Long categoryDTOId);
}
