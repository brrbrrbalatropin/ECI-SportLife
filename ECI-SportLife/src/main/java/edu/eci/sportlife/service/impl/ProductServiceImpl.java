package edu.eci.sportlife.service.impl;

import edu.eci.sportlife.exception.ResourceNotFoundException;
import edu.eci.sportlife.model.Product;
import edu.eci.sportlife.repository.ProductRepository;
import edu.eci.sportlife.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(String category, String name) {
        if (category != null) {
            return productRepository.findByCategoryAndStatusTrue(category);
        }
        if (name != null) {
            return productRepository.findByNameContainingIgnoreCaseAndStatusTrue(name);
        }
        return productRepository.findByStatusTrue();
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .filter(Product::getStatus)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}