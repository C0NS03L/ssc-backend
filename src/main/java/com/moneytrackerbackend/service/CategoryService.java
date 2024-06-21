package com.moneytrackerbackend.service;

import com.moneytrackerbackend.dto.CategoryDto;
import com.moneytrackerbackend.model.Category;
import com.moneytrackerbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public CategoryDto getCategory(Long id) {
    Category category = categoryRepository.findById(id).orElse(null);
    if (category == null) {
      return null;
    }
    CategoryDto categoryDto = new CategoryDto();
    categoryDto.setId(category.getId());
    categoryDto.setName(category.getName());
    categoryDto.setDescription(category.getDescription());
    return categoryDto;
  }

  public Category createCategory(CategoryDto categoryDto) {
    Category category = new Category();
    category.setName(categoryDto.getName());
    category.setDescription(categoryDto.getDescription());
    return categoryRepository.save(category);
  }

  public Category updateCategory(Long id, CategoryDto categoryDto) {
    Category category = categoryRepository.findById(id).orElse(null);
    if (category == null) {
      return null;
    }
    category.setName(categoryDto.getName());
    category.setDescription(categoryDto.getDescription());
    return categoryRepository.save(category);
  }

  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }
}
