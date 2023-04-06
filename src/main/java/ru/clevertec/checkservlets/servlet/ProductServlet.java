package ru.clevertec.checkservlets.servlet;

import jakarta.servlet.annotation.WebServlet;
import ru.clevertec.checkservlets.model.Product;
import ru.clevertec.checkservlets.service.ProductService;

@WebServlet(value = "/api/products")
public class ProductServlet extends CrudServlet<Product> {

    {
        service = ProductService.getInstance();
    }
}
