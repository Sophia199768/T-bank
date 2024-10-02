package com.example.tbank.mapper;


import com.example.tbank.dto.CategoryDto;
import com.example.tbank.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto toGetCategoryDto(Category category) {
        return new CategoryDto(category.getId(), category.getSlug(), category.getName());
    }


    public Category toNewCategory(CategoryDto categoryDto) {
        return new Category(categoryDto.getId(), categoryDto.getSlug(), categoryDto.getName());
    }
}
