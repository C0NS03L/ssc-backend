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
  public Category getCategory(@PathVariable Long id) {
    return categoryService.getCategory(id);
  }

  @PostMapping
  public Category createCategory(@RequestBody CategoryDto categoryDto) {
    Category category = new Category();
    category.setName(categoryDto.getName());
    return categoryService.createCategory(category);
  }

  @PutMapping("/{id}")
  public Category updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
    Category category = new Category();
    category.setId(id);
    category.setName(categoryDto.getName());
    return categoryService.updateCategory(id, category);
  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
  }
}
