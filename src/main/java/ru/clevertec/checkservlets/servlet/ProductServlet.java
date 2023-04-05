package ru.clevertec.checkservlets.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.checkservlets.model.Product;
import ru.clevertec.checkservlets.service.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/api/products")
public class ProductServlet extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();
    private static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * Handles HTTP GET requests for getting products from database, with pagination.
     * <br/>
     * Possible variants of arguments: <br/>
     * id - returns product with such id <br/>
     * page&limit(by default limit is 20) - returns list of products with limit size/or smaller<br/>
     * empty - returns all products from database
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        String id = request.getParameter("id");
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");
        Gson gson = new Gson();
        if (id != null) {
            Product product = productService.find(Integer.parseInt(id));
            writer.write(gson.toJson(product));
        } else if (pageParam != null) {
            int limit = DEFAULT_PAGE_SIZE;
            if (limitParam != null) {
                limit = Integer.parseInt(limitParam);
            }
            int page = Integer.parseInt(pageParam);
            List<Product> content = productService.findAll(page, limit);
            writer.write(gson.toJson(content));
        } else {
            List<Product> content = productService.findAll();
            writer.write(gson.toJson(content));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendError(400, "id parameter must be passed!");
        } else {
            int id = Integer.parseInt(idParam);
            productService.delete(id);
            response.setStatus(204);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        Gson gson = new Gson();
        Product newProduct = gson.fromJson(data, Product.class);
        productService.create(newProduct);
    }
}
