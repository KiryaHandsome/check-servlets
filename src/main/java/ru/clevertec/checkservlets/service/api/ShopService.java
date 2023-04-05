package ru.clevertec.checkservlets.service.api;

import java.util.List;

public interface ShopService<T> {

    void create(T product);

    T find(int id);

    List<T> findAll(int page, int limit);

    List<T> findAll();

    T update(int id, T object);

    public void delete(int id);
}
