package ru.clevertec.checkservlets.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.checkservlets.model.api.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product implements Identifiable {

    private int id;
    private String name;
    private double price;
    private boolean isPromotional;

    public Product(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.isPromotional = rs.getBoolean("is_promotional");
        this.price = rs.getDouble("price");
    }
}
