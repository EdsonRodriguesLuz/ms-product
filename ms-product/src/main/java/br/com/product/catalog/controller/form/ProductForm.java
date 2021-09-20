package br.com.product.catalog.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import br.com.product.catalog.model.Product;
import br.com.product.catalog.repository.ProductRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {

	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull @Positive
	private double price;

	public Product created() {
		return new Product(name, description, price);
	}
	
	public Product atualizar(Long id, ProductRepository productRepository) {
		Product product = productRepository.getOne(id);
		
		product.setName(this.name);
		product.setDescription(this.description);
		product.setPrice(this.price);
		
		return product;
	}

}
