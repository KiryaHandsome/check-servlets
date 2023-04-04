package ru.clevertec.dao;

import ru.clevertec.dao.api.CrudDao;
import ru.clevertec.model.Product;

import java.util.List;

public class ProductDao implements CrudDao<Product> {

    @Override
    public Product create(Product entity) {
        return null;
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
