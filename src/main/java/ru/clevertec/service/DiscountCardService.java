package ru.clevertec.service;

import ru.clevertec.dao.DiscountCardDao;
import ru.clevertec.dao.api.CrudDao;
import ru.clevertec.model.DiscountCard;
import ru.clevertec.service.api.ShopService;

import java.util.List;

public class DiscountCardService implements ShopService<DiscountCard> {

    private final CrudDao<DiscountCard> discountCardDao;

    public DiscountCardService(DiscountCardDao discountCardDao) {
        this.discountCardDao = discountCardDao;
    }

    @Override
    public DiscountCard create(DiscountCard product) {
        return discountCardDao.create(product);
    }

    @Override
    public DiscountCard find(int id) {
        return discountCardDao.read(id);
    }

    @Override
    public List<DiscountCard> findAll() {
        return discountCardDao.readAll();
    }

    @Override
    public DiscountCard update(int id, DiscountCard object) {
        object.setId(id);
        return discountCardDao.create(object);
    }


    public void delete(int id) {
        discountCardDao.delete(id);
    }
}
