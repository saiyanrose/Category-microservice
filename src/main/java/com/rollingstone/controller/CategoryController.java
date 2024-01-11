package com.rollingstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rollingstone.events.AbstractController;
import com.rollingstone.events.CategoryEvent;
import com.rollingstone.exceptions.ResourceNotFoundException;
import com.rollingstone.models.Category;
import com.rollingstone.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController extends AbstractController{

	@Autowired
	private CategoryService categoryService;

	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Category category) {
		Category saveCategory = categoryService.save(category);
		CategoryEvent categoryEventFetchById = new CategoryEvent("Category is saved with id: " + saveCategory.getId(), saveCategory);
		eventPublisher.publishEvent(categoryEventFetchById);
		return ResponseEntity.ok().body("New Category has been saved with ID:" + saveCategory.getId());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Category category = new Category();
		try {
			category = categoryService.getCategoryById(id);
			CategoryEvent categoryEventFetchById = new CategoryEvent("Category is fechted with id: " + id, category);
			eventPublisher.publishEvent(categoryEventFetchById);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}		
		return ResponseEntity.ok().body(category);
	}
	
	@GetMapping("")
	public ResponseEntity<Page<Category>> list(@RequestParam(value = "pageNum",defaultValue = "0")Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize) {
		Page<Category> categoryList = categoryService.getCategoryByPage(pageNum,pageSize);
		return ResponseEntity.ok().body(categoryList);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Category category) {
		try {
			categoryService.updateCategory(id,category);
		}catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}		
		return ResponseEntity.ok().body("Category has been updated successfully.");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			categoryService.deleteCategory(id);
		}catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}		
		return ResponseEntity.ok().body("Category has been deleted successfully.");
	}
}