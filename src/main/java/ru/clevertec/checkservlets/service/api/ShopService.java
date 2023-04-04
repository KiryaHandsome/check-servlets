package ru.clevertec.checkservlets.service.api;

import java.util.List;

public interface ShopService<T> {

    void create(T product);

    T find(int id);

    List<T> findAll(int pageNumber, int...pageSizeArgs);

    T update(int id, T object);

    public void delete(int id);
}
