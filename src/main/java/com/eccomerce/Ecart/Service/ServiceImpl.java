package com.eccomerce.Ecart.Service;

import com.eccomerce.Ecart.Exceptions.ApiEXception;
import com.eccomerce.Ecart.Exceptions.ResourceNotFoundException;
import com.eccomerce.Ecart.Model.Category;
import com.eccomerce.Ecart.payload.CategoryDTO;
import com.eccomerce.Ecart.payload.ResponseCategory;
import com.eccomerce.Ecart.repositories.CategoryRepostory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl implements CategoryService{
//    private List<Category> categories = new ArrayList<>();
    @Autowired
    private CategoryRepostory categoryRepostory;

    @Autowired
    private ModelMapper modelMapper;
//    private Long id = 1L;
    @Override
    public ResponseCategory getAllCategories(Integer pageNumber, Integer pageSize,String SortBy,String SortOrder) {
//        return categories;
        Sort sortByOrder = SortOrder.equalsIgnoreCase("Asc")?Sort.by(SortBy).ascending():Sort.by(SortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortByOrder);
        Page<Category> page = categoryRepostory.findAll(pageable);
//        List<Category> categories = categoryRepostory.findAll();
        List<Category> categories = page.getContent();
        if(categories.isEmpty()){
            throw new ApiEXception("Category is empty");
        }
        List<CategoryDTO> categoryDTOS = categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).toList();
        ResponseCategory responseCategory = new ResponseCategory();
        responseCategory.setContent(categoryDTOS);
        responseCategory.setPageNumber(page.getNumber());
        responseCategory.setPageSize(page.getSize());
        responseCategory.setTotalElement(page.getTotalElements());
        responseCategory.setTotalPages(page.getTotalPages());
        responseCategory.setLastPages(page.isLast());
        return responseCategory;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category savedCategory = categoryRepostory.findByCategoryName(category.getCategoryName());
        if(savedCategory != null){
            throw new ApiEXception("This is already existing in the list ");
        }
//        category.setCategoryId(id++);
//        categories.add(category);.
       Category savedcategory = categoryRepostory.save(category);
        return modelMapper.map(savedcategory,CategoryDTO.class);
    }
    @Override
    public CategoryDTO deleteCategory(Long CategoryId){
        Optional<Category> categories = categoryRepostory.findById(CategoryId);
         Category category = categories.orElseThrow(()->new ResourceNotFoundException("Category","categoryName",CategoryId));
//        Category category = categories.stream().filter(category1 -> category1.getCategoryId().equals(CategoryId))
//                .findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
         categoryRepostory.delete(category);
        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO,Long categoryDTOId) {

        Optional<Category> categories = categoryRepostory.findById(categoryDTOId);
        Category category1 = categories.orElseThrow(()-> new ResourceNotFoundException("Category","categoryName",categoryDTOId));
//        ResponseCategory responseCategory = new ResponseCategory();
//        category1.setCategoryName(category.getCategoryName());
//        category1.setCategoryId(category.getCategoryId());
//        category1.setCategoryName(category.getCategoryName());
        Category category = modelMapper.map(categoryDTO, Category.class);
//        category1.setCategoryId(category.getCategoryId());
        category1.setCategoryName(category.getCategoryName());
        Category savedCategory = categoryRepostory.save(category1);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }
}
