package com.eccomerce.Ecart.repositories;

import com.eccomerce.Ecart.Model.Category;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepostory extends JpaRepository<Category,Long>{
    Category findByCategoryName(String CategoryName);
}
