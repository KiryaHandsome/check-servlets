package ru.clevertec.checkservlets.util;

import lombok.experimental.UtilityClass;
import ru.clevertec.checkservlets.model.Product;

import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class CheckUtil {

    public static final String CASH_RECEIPT = "CASH RECEIPT";
    public static final String SHOP_NAME = "Clevertec SHOP";
    public static final String PROMOTION_CLAUSE = "PROM. - promotional product;\n" +
            "If quantity of promotional product > 5 \n" +
            "then u get discount 10% on this position!\n\n";
    public static final char CURRENCY_SYMBOL = '$';
    public static final String QUANTITY = "QTY";
    public static final String PROMOTIONAL = "PROM.";
    public static final String PRICE = "PRICE";
    public static final String TOTAL = "TOTAL";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String COST = "COST";
    public static final String TOTAL_COST = "TOTAL COST";
    public static final String DISCOUNT = "DISCOUNT";
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.now().format(formatter);
    }

    public static String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.now().format(formatter);
    }

    public static int numberOfDigits(int num) {
        return (int) Math.log10(num) + 1;
    }

    public static double getPromotionalPrice(Product product, int quantity) {
        return product.getPrice() * quantity * (product.isPromotional() && (quantity > 5) ? 0.9 : 1);
    }

    public static String priceToString(double price) {
        return decimalFormat.format(price) + CURRENCY_SYMBOL;
    }

    public static String[] parseParams(Map<String, String> params) {
        return params.entrySet()
                .stream()
                .map(e -> e.getKey() + "-" + e.getValue())
                .toArray(String[]::new);
    }

    /**
     * if value of returned map equals to 0, key - discount card id
     * Arguments should be given in format 'id-qty ... card-id'
     *
     * @return map: key - id, value - quantity
     */
    public static Map<Integer, Integer> parseArguments(String... args) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String arg : args) {
            String[] values = arg.split("-");
            if (values.length != 2) {
                throw new InvalidParameterException("Invalid argument in command line.");
            }
            if ("card".equalsIgnoreCase(values[0])) {
                Integer cardId = Integer.parseInt(values[1]);
                map.put(cardId, 0);
            } else {
                int productId = Integer.parseInt(values[0]);
                int quantity = Integer.parseInt(values[1]);
                if (productId <= 0 || quantity <= 0) {
                    throw new InvalidParameterException("Id and quantity must be greater than 0.");
                }
                int previousQuantity = map.getOrDefault(productId, 0);
                map.put(productId, previousQuantity + quantity);  //case when map has this product already
            }
        }
        return map;
    }
}
