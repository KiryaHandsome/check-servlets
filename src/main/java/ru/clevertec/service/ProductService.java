package ru.clevertec.service;

import ru.clevertec.dao.ProductDao;
import ru.clevertec.dao.api.CrudDao;
import ru.clevertec.model.Product;
import ru.clevertec.service.api.ShopService;

import java.util.List;

public class ProductService implements ShopService<Product> {

    private final CrudDao<Product> productDao;

    public ProductService(ProductDao discountCardDao) {
        this.productDao = discountCardDao;
    }

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product find(int id) {
        return productDao.read(id);
    }

    @Override
    public List<Product> findAll() {
        return productDao.readAll();
    }

    @Override
    public Product update(int id, Product object) {
        object.setId(id);
        return productDao.create(object);
    }


    public void delete(int id) {
        productDao.delete(id);
    }
}
