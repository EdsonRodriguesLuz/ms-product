package br.com.product.catalog.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.product.catalog.controller.form.ProductForm;
import br.com.product.catalog.model.Product;
import br.com.product.catalog.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> lista() {
		return productService.lista();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Product> cadastrar(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
		return productService.cadastrar(form, uriBuilder);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> detalhar(@PathVariable Long id) {
		return productService.detalhar(id);
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam(required = false)String q,@RequestParam(required = false) Double min_price,@RequestParam(required = false) Double max_price){       
        return productService.search(q, min_price, max_price);
    }
		
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Product> atualizar(@PathVariable Long id, @RequestBody @Valid ProductForm form) {
		return productService.atualizar(id, form);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		return productService.remover(id);
	}

}