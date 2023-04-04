package ru.clevertec.checkservlets.dao;

import ru.clevertec.checkservlets.dao.api.CrudDao;
import ru.clevertec.checkservlets.model.DiscountCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiscountCardDao implements CrudDao<DiscountCard> {

    private static final String INSERT_INTO_QUERY = "INSERT INTO discount_card(discount) VALUES(?);";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM discount_card WHERE id=?;";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM discount_card;";
    private static final String UPDATE_QUERY = "UPDATE discount_card SET discount=? WHERE id=?;";
    private static final String DELETE_QUERY = "DELETE FROM discount_card WHERE id=?;";


    @Override
    public void create(DiscountCard entity) {
        try (Connection connection = DaoDataSource.getConnection();
             PreparedStatement creatPs = connection.prepareStatement(INSERT_INTO_QUERY)) {
            creatPs.setFloat(1, entity.getDiscount());
            creatPs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DiscountCard read(int id) {
        DiscountCard discountCard = null;
        try (Connection connection = DaoDataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            discountCard = new DiscountCard(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountCard;
    }

    @Override
    public List<DiscountCard> readAll() {
        List<DiscountCard> discountCards = new ArrayList<>();
        try (Connection connection = DaoDataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                discountCards.add(new DiscountCard(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountCards;
    }

    @Override
    public DiscountCard update(int id, DiscountCard newEntity) {
        DiscountCard discountCard = null;
        try (Connection connection = DaoDataSource.getConnection();
             PreparedStatement updatePs = connection.prepareStatement(UPDATE_QUERY);
             PreparedStatement selectPs = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            //update row
            updatePs.setFloat(1, newEntity.getDiscount());
            updatePs.setInt(2, id);
            updatePs.executeUpdate();
            //select updated row
            selectPs.setInt(1, id);
            ResultSet resultSet = selectPs.executeQuery();
            resultSet.next();
            discountCard = new DiscountCard(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountCard;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DaoDataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}