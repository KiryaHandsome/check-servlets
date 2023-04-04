package ru.clevertec.checkservlets.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.checkservlets.model.DiscountCard;
import ru.clevertec.checkservlets.model.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    private ProductDao productDao;

    @BeforeEach
    void setUp() {
        productDao = new ProductDao();
    }

    @Test
    void checkCreate() {
        Product newProduct = Product.builder()
                .price(1012.12)
                .name("Milk")
                .build();
        productDao.create(newProduct);
    }

    @Test
    void checkReadAll() {
        System.out.println(productDao.readAll(3));
    }

    @Test
    void checkUpdate() {
        Product product = Product.builder()
                .price(7181.87)
                .name("Bread")
                .build();
        System.out.println(productDao.update(1, product));
    }

    @Test
    void checkDelete() {
        productDao.delete(3);
    }

}