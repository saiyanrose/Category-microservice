package com.rollingstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rollingstone.models.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {	

}
