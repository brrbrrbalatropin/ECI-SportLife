package edu.eci.sportlife.repository;

import edu.eci.sportlife.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByStatusTrue();

    List<Product> findByCategoryAndStatusTrue(String category);

    List<Product> findByNameContainingIgnoreCaseAndStatusTrue(String name);
}