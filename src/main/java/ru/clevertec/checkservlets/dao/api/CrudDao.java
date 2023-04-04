package ru.clevertec.checkservlets.dao.api;

import java.util.List;

public interface CrudDao<T> {

    void create(T entity);

    T read(int id);

    List<T> readAll(int pageNumber, int...pageSizeArgs);

    T update(int id, T newEntity);

    void delete(int id);
}
