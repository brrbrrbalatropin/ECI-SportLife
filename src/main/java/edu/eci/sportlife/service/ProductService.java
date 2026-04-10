package edu.eci.sportlife.service;

import edu.eci.sportlife.model.Product;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getAllProducts(String category, String name);

    Product getProductById(UUID id);
}