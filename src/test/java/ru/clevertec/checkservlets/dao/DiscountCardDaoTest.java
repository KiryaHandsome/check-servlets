package ru.clevertec.checkservlets.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.checkservlets.model.DiscountCard;

class DiscountCardDaoTest {

    private DiscountCardDao discountCardDao;

    @BeforeEach
    void setUp() {
        discountCardDao = new DiscountCardDao();
    }

    @Test
    void checkCreate() {
        DiscountCard newDiscountCard = new DiscountCard(1, 0.9f);
        discountCardDao.create(newDiscountCard);
    }

    @Test
    void checkReadAll() {
        System.out.println(discountCardDao.readAll(1,10));
    }

    @Test
    void checkUpdate() {
        DiscountCard discountCard = new DiscountCard(1, 0.77f);
        System.out.println(discountCardDao.update(1, discountCard));
    }

    @Test
    void checkDelete() {
        discountCardDao.delete(3);
    }
}