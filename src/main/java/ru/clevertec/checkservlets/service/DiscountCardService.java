package ru.clevertec.checkservlets.service;

import ru.clevertec.checkservlets.dao.DiscountCardDao;
import ru.clevertec.checkservlets.dao.api.CrudDao;
import ru.clevertec.checkservlets.model.DiscountCard;
import ru.clevertec.checkservlets.service.api.ShopService;

import javax.inject.Inject;
import java.util.List;

public class DiscountCardService implements ShopService<DiscountCard> {

    private final CrudDao<DiscountCard> discountCardDao;

    @Inject
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
