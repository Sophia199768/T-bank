package com.example.tbank.services;

import com.example.tbank.dto.CategoryDto;
import com.example.tbank.mapper.CategoryMapper;
import com.example.tbank.models.Category;
import com.example.tbank.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> findAllCategories() {
        return repository.readAll().stream().map(categoryMapper::toGetCategoryDto).toList();
    }

    public CategoryDto getCategoryById(Integer id) {
        Category category = repository.get(id);
        if (category == null) {
            throw new NotFoundException("Not found");
        }
        return categoryMapper.toGetCategoryDto(repository.get(id));
    }

    public void postCategory(CategoryDto categoryDto) {
        repository.create(categoryMapper.toNewCategory(categoryDto));
    }

    public void deleteCategory(Integer id) {
        Category category = repository.get(id);
        if (category == null) {
            throw new NotFoundException("Not found");
        }

        repository.delete(id);
    }
}




