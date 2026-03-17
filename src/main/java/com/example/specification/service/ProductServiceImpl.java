package com.example.specification.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.example.specification.model.Product;
import com.example.specification.dto.ProductDto;
import com.example.specification.repository.ProductJpa;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.jpa.domain.Specification;

import com.example.specification.specification.ProductSpecification;
import com.example.specification.repository.UserRepostory;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductJpa productRepo;
    private final UserRepostory userRepostory;

    @Override
    public void saveUserProduct(Product product, Long userId) {
        var user = userRepostory.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        product.setUsermodel(user);
        if (user.getProducts() == null) {
            user.setProducts(new ArrayList<>());
        }
        user.getProducts().add(product);
        productRepo.save(product);
    }

    @Override
    public Double totalPrice() {
        return productRepo.totalPrice();
    }

    @Override
    public List<Product> getByActiveOrId(Long id, Boolean active,
            String name, Double minPrice,
            Double maxPrice, Long userId, LocalDateTime dateTime,
            LocalDateTime start, LocalDateTime end, String... status) {

        Specification<Product> spec = buildSpec(id, active, name, minPrice, maxPrice, userId, dateTime, start, end, status);
        return productRepo.findAll(spec);
    }

    @Override
    public Double getTotalPriceBySpec(Long id, Boolean active,
            String name, Double minPrice,
            Double maxPrice, Long userId, LocalDateTime dateTime,
            LocalDateTime start, LocalDateTime end, String... status) {

        Specification<Product> spec = buildSpec(id, active, name, minPrice, maxPrice, userId, dateTime, start, end, status);
        return productRepo.findAll(spec).stream()
                .filter(p -> p.getPrice() != null)
                .mapToDouble(Product::getPrice)
                .sum();
    }

    /**
     * COMBINED: Specification + JPQL in one method.
     *
     * Step 1 — Specification: dynamically filter products by any optional params.
     * Step 2 — JPQL: ask the DB for the total price of ALL products of this user
     *           (a trusted DB-level aggregate, not affected by filters).
     *
     * Real-world use-case: seller's product search page.
     *   - They search/filter their own products (Specification).
     *   - The page also shows "Your total portfolio value: $X" (JPQL).
     *   Both come back in one API call.
     */
    @Override
    public ProductDto searchWithSummary(Long userId, Long id, Boolean active,
            String name, Double minPrice, Double maxPrice,
            LocalDateTime dateTime, LocalDateTime start, LocalDateTime end,
            String... status) {

        // STEP 1 — Specification: flexible, any filter can be null
        Specification<Product> spec = buildSpec(id, active, name, minPrice, maxPrice, userId, dateTime, start, end, status);
        List<Product> filtered = productRepo.findAll(spec);

        // aggregate the filtered list in memory
        double filteredTotal = filtered.stream()
                .filter(p -> p.getPrice() != null)
                .mapToDouble(Product::getPrice)
                .sum();

        // STEP 2 — JPQL: single DB aggregate query for the user's full total
        Double userTotal = (userId != null) ? productRepo.totalPriceByUserId(userId) : productRepo.totalPrice();

        return new ProductDto(filtered, filtered.size(), filteredTotal, userTotal);
    }

    // shared spec builder — avoids duplication across methods
    private Specification<Product> buildSpec(Long id, Boolean active, String name,
            Double minPrice, Double maxPrice, Long userId,
            LocalDateTime dateTime, LocalDateTime start, LocalDateTime end,
            String... status) {
        return Specification
                .where(ProductSpecification.productById(id))
                .and(ProductSpecification.isActive(active))
                .and(ProductSpecification.nameContains(name))
                .and(ProductSpecification.priceBetween(minPrice, maxPrice))
                .and(ProductSpecification.statusIn(status))
                .and(ProductSpecification.userIdEquals(userId))
                .and(ProductSpecification.createAfter(dateTime))
                .and(ProductSpecification.createdBetween(start, end));
    }
}
