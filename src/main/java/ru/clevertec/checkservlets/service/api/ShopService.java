package ru.clevertec.checkservlets.service.api;

import java.util.List;

public interface ShopService<T> {

    Integer create(T product);

    T find(int id);

    List<T> findAll(int page, int limit);

    List<T> findAll();

    T update(T object);

    void delete(int id);
}
