package backend.project.service;

import backend.project.dto.dto.ProductDTO;
import backend.project.exception.*;
import backend.project.model.Product;
import backend.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public ProductDTO addProduct(Product product) throws AlreadyExistException {
        return new ProductDTO(productRepository.save(product));
    }

    public Product getProduct(long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product " + id + " not found"));
    }

    public ProductDTO updateProduct(long id, Product product) throws ProductNotFoundException {
        Product oldProduct = getProduct(id);
        if(oldProduct == null)
            throw new ProductNotFoundException("Product " + id + " not found");
        if(product.getName() != null)
            oldProduct.setName(product.getName());
        if(product.getImageUrl() != null)
            oldProduct.setImageUrl(product.getImageUrl());
        if(product.getPrice() > 0)
            oldProduct.setPrice(product.getPrice());
        if(product.getCategory() != null)
            oldProduct.setCategory(product.getCategory());
        return new ProductDTO(productRepository.save(oldProduct));
    }

    public void deleteProduct(long id) throws ProductNotFoundException {
        Product product = getProduct(id);
        if(product == null)
            throw new ProductNotFoundException("Product " + id + " not found");
        productRepository.delete(product);
    }
}
