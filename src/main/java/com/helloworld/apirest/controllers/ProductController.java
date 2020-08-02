package com.helloworld.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helloworld.apirest.models.Product;
import com.helloworld.apirest.repository.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
@RestController
@RequestMapping(value="/api")
@Api(value="API REST Products")
@CrossOrigin(origins="*")
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/products")
	@ApiOperation(value="Return a list of products")
	public List<Product> listProducts() {
		return productRepository.findAll();
	}
	
	@GetMapping("/products/{id}")
	@ApiOperation(value="Return an especific product by id")
	public Product listProduct(@PathVariable(value="id") long id) {
		return productRepository.findById(id);
	}
	
	@PostMapping("/products")
	@ApiOperation(value="Create a product")
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@PutMapping("/products")
	@ApiOperation(value="Update a product")
	public Product updateProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@DeleteMapping("/products/{id}")
	@ApiOperation(value="Delete an especific product by id")
	public String deleteProduct(@PathVariable(value="id") long id) {
		try {
			productRepository.deleteById(id);
			
			return String.format("produto com id %d foi removido com sucesso!", id);
		}
		catch (Exception e) {
			return String.format("Erro ao deletar o produto: %s", e.getMessage());
		}
	}
}
