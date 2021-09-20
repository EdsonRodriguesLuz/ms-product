package br.com.product.catalog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private double price;
	
	public Product() {
	}
	
	public Product(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Product(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}
	
	public Product(Long id,String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.id = id;
	}
}
