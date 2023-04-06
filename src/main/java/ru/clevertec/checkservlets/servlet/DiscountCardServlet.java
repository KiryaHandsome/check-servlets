package ru.clevertec.checkservlets.servlet;


import jakarta.servlet.annotation.WebServlet;
import ru.clevertec.checkservlets.model.DiscountCard;
import ru.clevertec.checkservlets.service.DiscountCardService;

@WebServlet(value = "/api/discount-cards")
public class DiscountCardServlet extends CrudServlet<DiscountCard> {

    {
        service = DiscountCardService.getInstance();
    }
}
