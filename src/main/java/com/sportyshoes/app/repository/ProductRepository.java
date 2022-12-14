package com.sportyshoes.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sportyshoes.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findByID(int id);
	
	@Query("Select id from Product where category Like %?1%")
	List<Integer> getByCategory(String keyword);
	
	@Query("SELECT p FROM Product p WHERE p.company LIKE %?1%"
			+" OR p.name LIKE %?1%"
			+" OR p.price LIKE %?1%"
			+" OR p.category LIKE %?1%")
	public List<Product> homeSearch(String keyword);

}
