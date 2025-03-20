package com.eccomerce.Ecart.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.annotation.processing.Generated;

@Entity(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
//    @NotBlank(message = "Categoryname need not to be blank")
    private String categoryName;

//    public Category() {
//    }

//    public Category(Long categoryId, String categoryName) {
//        CategoryId = categoryId;
//        CategoryName = categoryName;
//    }
//
}
