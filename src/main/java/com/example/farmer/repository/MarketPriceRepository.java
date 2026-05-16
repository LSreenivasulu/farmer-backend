package com.example.farmer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.farmer.model.MarketPrice;

import java.util.List;

public interface MarketPriceRepository extends JpaRepository<MarketPrice, Long> {

    List<MarketPrice> findByProductNameIgnoreCase(String productName);
}