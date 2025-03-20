package com.eccomerce.Ecart.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCategory {

    List<CategoryDTO> content;
    private Integer PageNumber;
    private Integer PageSize;
    private Long TotalElement;
    private Integer TotalPages;
    private Boolean lastPages;
}
