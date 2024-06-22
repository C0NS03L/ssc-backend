package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.dto.CategoryDto;
import com.moneytrackerbackend.model.AppUser;
import com.moneytrackerbackend.model.Category;
import com.moneytrackerbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public List<Category> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @GetMapping("/{id}")
  public CategoryDto getCategory(@PathVariable Long id) {
    Category category = categoryService.getCategory(id);
    return new CategoryDto(category.getId(), category.getName(), category.getUser().getId());
  }

  @PostMapping
  public Category createCategory(@RequestBody CategoryDto categoryDto) {
    Category category = new Category();
    category.setName(categoryDto.getName());
    category.setUser(new AppUser(categoryDto.getUserId()));
    return categoryService.createCategory(category);
  }

  @PutMapping("/{id}")
  public Category updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
    Category category = categoryService.getCategory(id);
    category.setName(categoryDto.getName());
    category.setUser(new AppUser(categoryDto.getUserId()));
    return categoryService.updateCategory(category);
  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
  }
}
