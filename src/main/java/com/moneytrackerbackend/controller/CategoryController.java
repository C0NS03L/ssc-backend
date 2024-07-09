package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.dto.CategoryDto;
import com.moneytrackerbackend.model.AppUser;
import com.moneytrackerbackend.model.Category;
import com.moneytrackerbackend.service.CategoryService;
import com.moneytrackerbackend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;
  private final UserService userService;

  public CategoryController(CategoryService categoryService, UserService userService) {
    this.categoryService = categoryService;
    this.userService = userService;
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
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = null;
    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      username = ((UserDetails) authentication.getPrincipal()).getUsername();
    }
    AppUser user = userService.findByUsername(username);

    Category category = new Category();
    category.setName(categoryDto.getName());
    category.setUser(user);

    return categoryService.createCategory(category);
  }

  @PutMapping("/{id}")
  public Category updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
    Category category = categoryService.getCategory(id);
    category.setName(categoryDto.getName());
    return categoryService.updateCategory(id, category);
  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
  }
}
