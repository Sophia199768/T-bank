package com.example.tbank.services;

import com.example.tbank.dto.CategoryDto;
import com.example.tbank.mapper.CategoryMapper;
import com.example.tbank.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getCategories() {
        return repository.read().stream().map(categoryMapper::toGetCategoryDto).toList();
    }


    public CategoryDto getCategoryById(Integer id) {
        return categoryMapper.toGetCategoryDto(repository.get(id));
    }

    public void postCategory(CategoryDto categoryDto) {
        repository.create(categoryMapper.toNewCategory(categoryDto));
    }

    public void deleteCategory(Integer id) {
        repository.delete(id);
    }
}




