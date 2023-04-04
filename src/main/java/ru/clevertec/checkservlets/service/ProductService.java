package ru.clevertec.checkservlets.service;

import ru.clevertec.checkservlets.dao.ProductDao;
import ru.clevertec.checkservlets.dao.api.CrudDao;
import ru.clevertec.checkservlets.model.Product;
import ru.clevertec.checkservlets.service.api.ShopService;

import javax.inject.Inject;
import java.util.List;

public class ProductService implements ShopService<Product> {

    private final CrudDao<Product> productDao;

    @Inject
    public ProductService(ProductDao discountCardDao) {
        this.productDao = discountCardDao;
    }

    @Override
    public void create(Product product) {
        productDao.create(product);
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
        return productDao.update(id, object);
    }


    public void delete(int id) {
        productDao.delete(id);
    }
}
