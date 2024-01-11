package com.rollingstone.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rollingstone.exceptions.ResourceNotFoundException;
import com.rollingstone.models.Category;
import com.rollingstone.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category save(Category category) {
		return categoryRepository.save(category);		
	}
	
	public Category getCategoryById(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isEmpty()) {
			throw new ResourceNotFoundException("Category not found with id: " + id);
		}
		return category.get();
	}

	public Page<Category> getCategoryByPage(Integer pageNum, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
		return categoryRepository.findAll(pageable);
	}

	public void updateCategory(Long id,Category category) {		
		Optional<Category> categoryExist = categoryRepository.findById(id);
		if (categoryExist.isEmpty()) {
			throw new ResourceNotFoundException("Category not found with id: " + id);
		}
		categoryExist.get().setCategoryName(category.getCategoryName());
		categoryExist.get().setCategoryDescription(category.getCategoryDescription());	
		
		categoryRepository.save(categoryExist.get());
	}
	
	public void deleteCategory(Long id) {
		Optional<Category> categoryExist = categoryRepository.findById(id);
		if (categoryExist.isEmpty()) {
			throw new ResourceNotFoundException("Category not found with id: " + id);
		}
		categoryRepository.deleteById(id);
	}

}
