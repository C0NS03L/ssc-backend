package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.dto.CategoryDto;
import com.moneytrackerbackend.model.Category;
import com.moneytrackerbackend.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public List<Category> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @GetMapping("/{id}")
  public CategoryDto getCategory(@PathVariable Long id) {
    return categoryService.getCategory(id);
  }

  @PostMapping
  public Category createCategory(@RequestBody CategoryDto categoryDto) {
    return categoryService.createCategory(categoryDto);
  }

  @PutMapping("/{id}")
  public Category updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
    return categoryService.updateCategory(id, categoryDto);
  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
  }
}
