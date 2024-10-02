package com.example.tbank.restControllers;

import com.example.tbank.aop.Loggable;
import com.example.tbank.dto.CategoryDto;
import com.example.tbank.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/places/categories")
@RequiredArgsConstructor
@Loggable
public class CategoryController {
   private final CategoryService categoryService;

   @GetMapping
   public ResponseEntity<List<CategoryDto>> getCategories() {
      return ResponseEntity.ok(categoryService.getCategories());
   }

   @GetMapping("/{id}")
   public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
      return ResponseEntity.ok(categoryService.getCategoryById(id));
   }

   @PutMapping
   public void putCity(@RequestBody CategoryDto categoryDto) {
      categoryService.postCategory(categoryDto);
   }

   @PostMapping
   public void postCategory(@RequestBody CategoryDto categoryDto) {
      categoryService.postCategory(categoryDto);
   }

   @DeleteMapping("/{id}")
   public void deleteCategory(@PathVariable Integer id) {
      categoryService.deleteCategory(id);
   }
}
