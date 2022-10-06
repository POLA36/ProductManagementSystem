package com.example.demo.dao;

import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductDao extends JpaRepository<Product, Integer> {

    List<Product> findAllByOrderByNameDesc();
    @Query(value = "SELECT p.name, COUNT(p.quantity) FROM Product AS p GROUP BY p.name")
    List<Object[]> countProductByName();
}
