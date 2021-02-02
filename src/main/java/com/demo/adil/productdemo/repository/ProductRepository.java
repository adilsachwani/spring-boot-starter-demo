package com.demo.adil.productdemo.repository;

import com.demo.adil.productdemo.models.Category;
import com.demo.adil.productdemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);

}