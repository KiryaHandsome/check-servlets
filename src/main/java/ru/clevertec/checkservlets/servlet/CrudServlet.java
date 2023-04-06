package ru.clevertec.checkservlets.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.checkservlets.model.api.Identifiable;
import ru.clevertec.checkservlets.service.api.ShopService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CrudServlet<T extends Identifiable> extends HttpServlet {

    protected ShopService<T> service;
    private final Class<T> persistentClass;
    private static final int DEFAULT_PAGE_SIZE = 20;

    {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Handles HTTP GET requests for getting entities from database, with pagination.
     * <br/>
     * Possible variants of URL arguments: <br/>
     * id - returns entity with such id <br/>
     * page&limit(by default limit is 20) - returns list of entities with limit size/or smaller<br/>
     * empty - returns all entities from database
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");

        String id = request.getParameter("id");
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");

        Gson gson = new Gson();
        if (id != null) {  // read by id case
            T entity = service.find(Integer.parseInt(id));
            writer.write(gson.toJson(entity));
        } else if (pageParam != null) {  // pagination read case
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
     * Handles HTTP DELETE requests for removing entities from database.
     * <br/>
     * URL arguments: id - id of entity to delete.
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendError(400, "id parameter must be passed!");
        } else {
            int id = Integer.parseInt(idParam);
            service.delete(id);
            response.setStatus(204);
        }
    }

    /**
     * Handles HTTP POST requests for adding entities to database.
     * <br/>
     * Request body must contain json representation of new entity.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = readRequestBody(request);
        T newEntity = new Gson().fromJson(body, persistentClass);
        service.create(newEntity);
    }

    /**
     * Handles HTTP PUT requests for updating entities in database.
     * <br/>
     * Request body must contain json as new representation of entity.
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = readRequestBody(request);
        T entity = new Gson().fromJson(body, persistentClass);
        service.update(entity.getId(), entity);
    }

    private static String readRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

}
