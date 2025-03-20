package com.eccomerce.Ecart.Controller;

import com.eccomerce.Ecart.Config.AppConstants;
import com.eccomerce.Ecart.Exceptions.ApiEXception;
import com.eccomerce.Ecart.Model.Category;
import com.eccomerce.Ecart.Service.CategoryService;
import com.eccomerce.Ecart.payload.CategoryDTO;
import com.eccomerce.Ecart.payload.ResponseCategory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/public/categories")
    public ResponseEntity<ResponseCategory> getAllCategories(@RequestParam(name = "pageNumber",defaultValue = AppConstants.PageNumber,required = false)
                                                                 Integer pageNumber,
                                                             @RequestParam(name = "pageSize",defaultValue = AppConstants.PageSize,required = false)
                                                             Integer pageSize,
                                                             @RequestParam(name = "sortBy",defaultValue = AppConstants.SortBy,required = false) String sortBy,
                                                             @RequestParam(name = "sortOrder",defaultValue = AppConstants.SortOrder,required = false) String sortOrder){
        ResponseCategory responseCategory = categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(responseCategory,HttpStatus.OK);
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return  new ResponseEntity<>(savedCategory,HttpStatus.CREATED);
    }
    @DeleteMapping("/api/admin/categories/{CategoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long CategoryId) {
            CategoryDTO category = categoryService.deleteCategory(CategoryId);
            return new ResponseEntity<>(category,HttpStatus.OK);


    }
    @PutMapping("/api/public/categories/{CategoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable Long CategoryId){
            CategoryDTO category = categoryService.updateCategory(categoryDTO,CategoryId);
            return new ResponseEntity<>(category,HttpStatus.OK);

    }
}
