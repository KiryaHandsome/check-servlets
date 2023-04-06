package ru.clevertec.checkservlets.dao.api;

import java.util.List;

public interface CrudDao<T> {

    /**
     * Creates new entity in database.
     *
     * @param entity new entity
     */
    void create(T entity);

    /**
     * Gets entity from database by id.
     *
     * @param id id of desired entity
     */
    T read(int id);

    /**
     * Gets entities from database with pagination.
     *
     * @param page  number of page
     * @param limit limit of fetch
     * @return list of entities
     */
    List<T> readAll(int page, int limit);

    /**
     * Gets all entities from database.
     *
     * @return list of all entities
     */
    List<T> readAll();

    /**
     * Updates entity in database by id.
     *
     * @param id        id of entity to update
     * @param newEntity updated entity
     */
    T update(int id, T newEntity);

    /**
     * Deletes entity from database by id.
     *
     * @param id id of entity to delete
     */
    void delete(int id);
}
