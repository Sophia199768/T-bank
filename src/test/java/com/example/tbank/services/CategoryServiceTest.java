package com.example.tbank.services;

import com.example.tbank.dto.CategoryDto;
import com.example.tbank.mapper.CategoryMapper;
import com.example.tbank.models.Category;
import com.example.tbank.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository repository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void findAllCategories_shouldReturnListOfCategories_whenCategoriesExist() {
        CategoryDto firstDto = new CategoryDto(1, null, null);
        CategoryDto secondDto = new CategoryDto(2, null, null);

        List<CategoryDto> expectedCategoryDtos = List.of(firstDto, secondDto);

        Category first = new Category(1, null, null);
        Category second = new Category(2, null, null);

        when(repository.readAll()).thenReturn(List.of(first, second));
        when(categoryMapper.toGetCategoryDto(first)).thenReturn(firstDto);
        when(categoryMapper.toGetCategoryDto(second)).thenReturn(secondDto);

        List<CategoryDto> categoryDtos = categoryService.findAllCategories();

        assertEquals(expectedCategoryDtos, categoryDtos);
    }

    @Test
    void findAllCategories_shouldReturnEmptyList_whenNoCategoriesExist() {
        when(repository.readAll()).thenReturn(new ArrayList<>());

        List<CategoryDto> categoryDtos = categoryService.findAllCategories();

        assertTrue(categoryDtos.isEmpty());
    }

    @Test
    void getCategoryById_shouldReturnCategoryDto_whenCategoryExists() {
        int categoryId = 1;
        Category category = new Category(categoryId, "CategoryName", null);
        CategoryDto expectedDto = new CategoryDto(categoryId, "CategoryName", null);

        when(repository.get(categoryId)).thenReturn(category);
        when(categoryMapper.toGetCategoryDto(category)).thenReturn(expectedDto);

        CategoryDto actualDto = categoryService.getCategoryById(categoryId);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    void getCategoryById_shouldThrowException_whenCategoryDoesNotExist() {
        int categoryId = 1;

        when(repository.get(categoryId)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryById(categoryId);
        });

        assertEquals("Not found", exception.getMessage());
    }

    @Test
    void postCategory_shouldReturnNothing_whenCategoryDoesNotExist() {
        int categoryId = 1;
        CategoryDto dto = new CategoryDto(categoryId, "CategoryName", null);
        categoryService.postCategory(dto);

        assertDoesNotThrow(() -> categoryService.postCategory(dto));
    }

    @Test
    void deleteCategory_shouldDeleteCategory_whenCategoryExists() {
        int categoryId = 1;


        Category category = new Category(categoryId, "CategoryToDelete", null);
        when(repository.get(categoryId)).thenReturn(category);

        categoryService.deleteCategory(categoryId);

        assertDoesNotThrow(() -> repository.delete(categoryId));
    }

    @Test
    void deleteCategory_shouldThrowException_whenCategoryDoesNotExist() {
        int categoryId = 1;

        when(repository.get(categoryId)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.deleteCategory(categoryId);
        });

        assertEquals("Not found", exception.getMessage());
    }
}