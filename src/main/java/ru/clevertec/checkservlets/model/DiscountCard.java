package ru.clevertec.checkservlets.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.checkservlets.model.api.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCard implements Identifiable {

    private int id;
    private float discount;

    public DiscountCard(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.discount = rs.getFloat("discount");
    }
}
