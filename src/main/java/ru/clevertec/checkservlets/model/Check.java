package ru.clevertec.checkservlets.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Check {

    private final Map<Product, Integer> products = new HashMap<>();
    private DiscountCard discountCard;

    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
    }

    public void addDiscountCard(DiscountCard discountCard) {
        if(Objects.nonNull(this.discountCard)) {
            throw new RuntimeException("Discount card already presented.");
        }
        this.discountCard = discountCard;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }
}