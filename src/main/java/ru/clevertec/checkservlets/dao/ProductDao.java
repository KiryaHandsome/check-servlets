package ru.clevertec.checkservlets.dao;

import ru.clevertec.checkservlets.model.Product;
import ru.clevertec.checkservlets.dao.api.CrudDao;

import java.util.List;

public class ProductDao implements CrudDao<Product> {

    @Override
    public void create(Product entity) {
    }

    @Override
    public Product read(int id) {
        return null;
    }

    @Override
    public List<Product> readAll() {
        return null;
    }

    @Override
    public Product update(int id, Product newEntity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
