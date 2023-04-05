package ru.clevertec.checkservlets.dao.api;

import java.util.List;

public interface CrudDao<T> {

    void create(T entity);

    T read(int id);

    List<T> readAll(int page, int limit);

    List<T> readAll();

    T update(int id, T newEntity);

    void delete(int id);
}
