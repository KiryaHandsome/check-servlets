package ru.clevertec.checkservlets.check;

import ru.clevertec.checkservlets.model.Check;
import ru.clevertec.checkservlets.model.DiscountCard;
import ru.clevertec.checkservlets.model.Product;
import ru.clevertec.checkservlets.service.DiscountCardService;
import ru.clevertec.checkservlets.service.ProductService;

import java.util.Map;

public class CheckGenerator {

    private static final ProductService productService;
    private static final DiscountCardService discountCardService;

    static {
        productService = ProductService.getInstance();
        discountCardService = DiscountCardService.getInstance();
    }

    private static void addDataFromDb(Map<Integer, Integer> info, Check check) {
        for (Map.Entry<Integer, Integer> pair : info.entrySet()) {
            int id = pair.getKey();
            int quantity = pair.getValue();
            if (quantity == 0) { //is discount card
                DiscountCard discountCard = discountCardService.find(id);
                if(discountCard == null) throw new RuntimeException("discount card with id " + id + " not found");
                check.addDiscountCard(discountCard);
            } else {
                Product product = productService.find(id);
                if(product == null) throw new RuntimeException("product with id " + id + " not found");
                check.addProduct(product, quantity);
            }
        }
    }

    public static Check generateCheck(Map<Integer, Integer> info) {
        Check check = new Check();
        addDataFromDb(info, check);
        return check;
    }
}
