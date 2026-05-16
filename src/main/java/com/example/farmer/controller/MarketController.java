package com.example.farmer.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.farmer.model.MarketPrice;
import com.example.farmer.repository.MarketPriceRepository;

@RestController
@RequestMapping("/api/market")
@CrossOrigin(origins = "*")
public class MarketController {

    private final MarketPriceRepository repo;

    public MarketController(MarketPriceRepository repo) {
        this.repo = repo;
    }

    // ✅ GET ALL
    @GetMapping
    public List<MarketPrice> getAllPrices() {
        return repo.findAll();
    }

    // ✅ ADD
    @PostMapping
    public MarketPrice addPrice(@RequestBody MarketPrice price) {
        return repo.save(price);
    }

    // ✅ UPDATE (🔥 MISSING BEFORE)
    @PutMapping("/{id}")
    public MarketPrice updatePrice(@PathVariable Long id, @RequestBody MarketPrice updated) {

        MarketPrice existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));

        existing.setProductName(updated.getProductName());
        existing.setMarketName(updated.getMarketName());
        existing.setPrice(updated.getPrice());
        existing.setLocation(updated.getLocation());

        return repo.save(existing);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String deletePrice(@PathVariable Long id) {
        repo.deleteById(id);
        return "Deleted";
    }

    // ✅ BEST PRICE
    @GetMapping("/best/{product}")
    public MarketPrice getBestPrice(@PathVariable String product) {

        return repo.findAll().stream()
                .filter(p -> p.getProductName().equalsIgnoreCase(product))
                .max((a, b) -> Double.compare(a.getPrice(), b.getPrice()))
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}