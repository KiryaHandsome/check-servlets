package ru.clevertec.checkservlets.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.checkservlets.model.api.Identifiable;
import ru.clevertec.checkservlets.service.api.ShopService;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;

public class CrudServlet<T extends Identifiable> extends HttpServlet {

    protected ShopService<T> service;
    private final Class<T> persistentClass;
    private static final int DEFAULT_PAGE_SIZE = 20;

    {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Handles HTTP GET requests for getting entities from database, with pagination. <br/>
     * Possible variants of URL arguments:  id - returns entity with such id <br/>
     * page&limit(optional - by default limit is 20) - returns list of entities with limit size/or smaller<br/>
     * empty - returns all entities from database
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");

        Integer id = ServletUtil.retrieveIdFromUri(request.getRequestURI());
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");

        Gson gson = new Gson();
        if (Objects.nonNull(id)) {  // read by id case
            T entity = service.find(id);
            writer.write(gson.toJson(entity));
        } else if (Objects.nonNull(pageParam)) {  // read with pagination case
            int limit = DEFAULT_PAGE_SIZE;
            if (limitParam != null) {
                limit = Integer.parseInt(limitParam);
            }
            int page = Integer.parseInt(pageParam);
            List<T> content = service.findAll(page, limit);
            writer.write(gson.toJson(content));
        } else {  // read all case
            List<T> content = service.findAll();
            writer.write(gson.toJson(content));
        }
    }

    /**
     * Handles HTTP DELETE requests for removing entities from database. <br/>
     * URL should contain id as last block. id - id of entity to delete.
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = ServletUtil.retrieveIdFromUri(request.getRequestURI());
        if (Objects.isNull(id)) {
            response.sendError(400, "Incorrect path. id parameter must be passed");
        } else {
            service.delete(id);
            response.setStatus(204);
        }
    }

    /**
     * Handles HTTP POST requests for creating entities in database. <br/>
     * Request body must contain json representation of new entity. <br/>
     * Sends redirect if entity is created successfully, return 500 code otherwise.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = ServletUtil.readRequestBody(request);
        T newEntity = new Gson().fromJson(body, persistentClass);
        Integer id = service.create(newEntity);
        if (Objects.isNull(id)) {
            response.sendError(500, "Entity wasn't created");
        } else {
            response.sendRedirect(request.getRequestURI() + "/" + id);
        }
    }

    /**
     * Handles HTTP PUT requests for updating entities in database. <br/>
     * Request body must contain json as new representation of entity.
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        String body = ServletUtil.readRequestBody(request);
        Gson gson = new Gson();
        T entity = gson.fromJson(body, persistentClass);
        T updatedEntity = service.update(entity);
        if (Objects.isNull(updatedEntity)) {
            response.sendError(500, "Entity wasn't updated.");
        } else {
            writer.write(gson.toJson(entity));
        }
    }
}
