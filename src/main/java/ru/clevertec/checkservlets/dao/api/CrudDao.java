package ru.clevertec.checkservlets.dao.api;

import java.util.List;

public interface CrudDao<T> {

    /**
     * Creates new entity in database.
     *
     * @param entity new entity
     * @return id of created entity
     */
    Integer create(T entity);

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
     * Updates row of newEntity.getId() number in database to entity.
     *
     * @param newEntity entity with id of updatable entity
     */
    T update(T newEntity);

    /**
     * Deletes entity from database by id.<br/>
     *
     * @param id id of entity to delete
     */
    void delete(int id);
}
