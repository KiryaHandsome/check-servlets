package ru.clevertec.checkservlets.service;

import ru.clevertec.checkservlets.dao.DiscountCardDao;
import ru.clevertec.checkservlets.dao.api.CrudDao;
import ru.clevertec.checkservlets.model.DiscountCard;
import ru.clevertec.checkservlets.service.api.ShopService;

import java.util.List;

public class DiscountCardService implements ShopService<DiscountCard> {

    private final CrudDao<DiscountCard> discountCardDao;
    private static DiscountCardService instance;

    public DiscountCardService() {
        this.discountCardDao = DiscountCardDao.getInstance();
    }

    public static DiscountCardService getInstance() {
        if(instance == null) {
            instance = new DiscountCardService();
        }
        return instance;
    }

    @Override
    public Integer create(DiscountCard product) {
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
    public List<DiscountCard> findAll(int page, int limit) {
        return discountCardDao.readAll(page, limit);
    }

    @Override
    public DiscountCard update(DiscountCard object) {
        return discountCardDao.update(object);
    }


    public void delete(int id) {
        discountCardDao.delete(id);
    }
}
