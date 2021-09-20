package br.com.product.catalog.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.product.catalog.controller.form.ProductForm;
import br.com.product.catalog.model.Product;
import br.com.product.catalog.repository.ProductRepository;
import br.com.product.catalog.repository.ProductSpecifications;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductSpecifications productSpecifications;
	
	public List<Product> lista() {		
		return productRepository.findAll();
	}
	
	public ResponseEntity<Product> cadastrar(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
		Product product = form.created();
		productRepository.save(product);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new Product(product));
	}
	
	public ResponseEntity<Product> detalhar(@PathVariable Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return ResponseEntity.ok(new Product(product.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
    public ResponseEntity<List<Product>> search(String q, Double min_price, Double max_price){       
        List<Product> products = productSpecifications.getProducts(q, min_price, max_price);
       
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
		
	
	public ResponseEntity<Product> atualizar(@PathVariable Long id, @RequestBody @Valid ProductForm form) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Product product = form.atualizar(id, productRepository);
			return ResponseEntity.ok(new Product(product));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}