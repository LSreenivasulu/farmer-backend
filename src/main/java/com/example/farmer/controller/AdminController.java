package com.example.farmer.controller;

import com.example.farmer.model.MarketPrice;
import com.example.farmer.repository.MarketPriceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final MarketPriceRepository repo;

    // ✅ Constructor Injection (BEST PRACTICE)
    public AdminController(MarketPriceRepository repo) {
        this.repo = repo;
    }

    // ✅ GET ALL MARKET PRICES
    @GetMapping("/all")
    public List<MarketPrice> getAll() {
        return repo.findAll();
    }

    // ✅ ADD MARKET PRICE
    @PostMapping("/add")
    public MarketPrice add(@RequestBody MarketPrice mp) {
        mp.setCreatedAt(java.time.LocalDateTime.now());
        return repo.save(mp);
    }

    // ✅ UPDATE MARKET PRICE
    @PutMapping("/update/{id}")
    public MarketPrice update(@PathVariable Long id, @RequestBody MarketPrice mp) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Market price not found with id: " + id);
        }
        
        mp.setId(id);
        mp.setCreatedAt(java.time.LocalDateTime.now());
        return repo.save(mp);
    }

    // ✅ DELETE MARKET PRICE
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("Market price not found with id: " + id);
        }

        repo.deleteById(id);
        return "Deleted Successfully";
    }
}