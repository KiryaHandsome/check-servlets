package ru.clevertec.checkservlets.service;

import ru.clevertec.checkservlets.dao.ProductDao;
import ru.clevertec.checkservlets.dao.api.CrudDao;
import ru.clevertec.checkservlets.model.Product;
import ru.clevertec.checkservlets.service.api.ShopService;

import java.util.List;

public class ProductService implements ShopService<Product> {

    private final CrudDao<Product> productDao;
    private static ProductService instance;

    public ProductService() {
        this.productDao = ProductDao.getInstance();
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    @Override
    public Integer create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product find(int id) {
        return productDao.read(id);
    }

    @Override
    public List<Product> findAll(int page, int limit) {
        return productDao.readAll(page, limit);
    }

    @Override
    public List<Product> findAll() {
        return productDao.readAll();
    }

    @Override
    public Product update(Product object) {
        return productDao.update(object);
    }

    public void delete(int id) {
        productDao.delete(id);
    }
}
