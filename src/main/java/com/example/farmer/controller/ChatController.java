package com.example.farmer.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.farmer.repository.MarketPriceRepository;
import com.example.farmer.model.MarketPrice;

import java.util.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private MarketPriceRepository repo;

    @PostMapping
    public Map<String, Object> chat(@RequestBody Map<String, String> body) {

        String userMessage = body.get("message");

        // ❌ Validate input
        if (userMessage == null || userMessage.isEmpty()) {
            return Map.of(
                    "success", false,
                    "reply", "Please enter a product name"
            );
        }

        // 🔍 Clean user input
        String product = userMessage.toLowerCase()
                .replace("price of", "")
                .trim();

        // 🔎 Search database
        List<MarketPrice> list = repo.findByProductNameIgnoreCase(product);

        // ✅ If data found
        if (!list.isEmpty()) {

            StringBuilder reply = new StringBuilder();

            // 🌾 Product Name
            reply.append("🌾 Product: ")
                    .append(product.toUpperCase())
                    .append("\n\n");

            for (MarketPrice m : list) {

                reply.append("📍 ")
                        .append(m.getMarketName())
                        .append(" (")
                        .append(m.getLocation())
                        .append(")\n");

                reply.append("💰 Price: ₹")
                        .append(m.getPrice())
                        .append(" per kg\n\n");
            }

            return Map.of(
                    "success", true,
                    "reply", reply.toString()
            );
        }

        // ❌ If no data found
        return Map.of(
                "success", true,
                "reply", "❌ No data found for \"" + product + "\".\nTry Tomato, Onion, Potato."
        );
    }
}