package ru.clevertec.dao.api;

import java.util.List;

public interface CrudDao<T> {

    T create(T entity);

    T read(int id);

    List<T> readAll();

    T update(int id, T newEntity);

    void delete(int id);
}
