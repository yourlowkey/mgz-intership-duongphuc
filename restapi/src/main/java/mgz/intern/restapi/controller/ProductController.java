package mgz.intern.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import mgz.intern.restapi.model.Product;
import mgz.intern.restapi.repository.ProductRepository;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	ProductController(ProductRepository repository) {
	    this.productRepository = repository;
	  }
	//get all prdt

	@GetMapping("/products")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public  List<Product> getAllProducts(){
		System.out.println(productRepository.findAll().toString());
		return productRepository.findAll();
	}
	//add new prdt
    @PostMapping("/products")
	@PreAuthorize("hasRole('ADMIN')")
    Product newProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

	@PutMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN')or hasRole('MODERATOR')")
	Product replaceEmployee(@RequestBody Product newProduct, @PathVariable Long id) {

		return productRepository.findById(id)
				.map(product -> {
					product.setProductName(newProduct.getProductName());
					product.setDescription(newProduct.getDescription());
					product.setPrice(newProduct.getPrice());
					return productRepository.save(product);
				})
				.orElseGet(() -> {
					newProduct.setId(id);
					return productRepository.save(newProduct);
				});
	}

	@DeleteMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	void deleteEmployee(@PathVariable Long id) {
		productRepository.deleteById(id);
	}

}
