package ru.clevertec.checkservlets.dao;

import ru.clevertec.checkservlets.dao.api.CrudDao;
import ru.clevertec.checkservlets.model.DiscountCard;

import java.util.List;

public class DiscountCardDao implements CrudDao<DiscountCard> {

    @Override
    public DiscountCard create(DiscountCard entity) {
        return null;
    }

    @Override
    public DiscountCard read(int id) {
        return null;
    }

    @Override
    public List<DiscountCard> readAll() {
        return null;
    }

    @Override
    public DiscountCard update(int id, DiscountCard newEntity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}