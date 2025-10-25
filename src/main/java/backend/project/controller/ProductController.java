package backend.project.controller;

import backend.project.dto.dto.ProductDTO;
import backend.project.exception.*;
import backend.project.model.Product;
import backend.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    private ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        try{
            return ResponseEntity.ok(productService.getProduct(id));
        } catch(ProductNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping("/{id}")
    private ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody Product product) throws ProductNotFoundException {
        try{
            return ResponseEntity.ok(productService.updateProduct(id, product));
        } catch(ProductNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    private ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        try{
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch(ProductNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
