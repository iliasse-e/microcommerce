package com.mcommandes.clients;

import com.mcommandes.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * Interface qui permet de faire la passerelle avec l'api Product
 * Similaire aux interface JPARepository
 */
@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductRestClient {

    @GetMapping(value = "/produits/{id}")
    @CircuitBreaker(name = "productService", fallbackMethod = "getDefaultProduct")
    Product findProductById(@PathVariable Integer id);

    default Product getDefaultProduct(Integer id, Exception e) {
        return new Product(id, "Non disponible", "Non disponible", "Non disponible", null);
    }
}
