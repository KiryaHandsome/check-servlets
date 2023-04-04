package ru.clevertec.service.api;

import java.util.List;

public interface ShopService<T> {

    T create(T product);

    T find(int id);

    List<T> findAll();

    T update(int id, T object);

    public void delete(int id);
}
