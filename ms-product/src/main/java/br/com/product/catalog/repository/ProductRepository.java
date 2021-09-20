package br.com.product.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.product.catalog.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
