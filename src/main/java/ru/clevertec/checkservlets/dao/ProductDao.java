package ru.clevertec.checkservlets.dao;

import ru.clevertec.checkservlets.model.DiscountCard;
import ru.clevertec.checkservlets.model.Product;
import ru.clevertec.checkservlets.dao.api.CrudDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements CrudDao<Product> {

    private static final String INSERT_INTO_QUERY = "INSERT INTO product(name, price, is_promotional) VALUES(?,?,?);";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM product WHERE id=?;";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM product;";
    private static final String UPDATE_QUERY = "UPDATE product SET name=?, price=?, is_promotional=? WHERE id=?;";
    private static final String DELETE_QUERY = "DELETE FROM product WHERE id=?;";


    @Override
    public void create(Product entity) {
        try (Connection connection = DaoDataSource.getConnection();
             PreparedStatement creatPs = connection.prepareStatement(INSERT_INTO_QUERY)) {
            creatPs.setString(1, entity.getName());
            creatPs.setDouble(2, entity.getPrice());
            creatPs.setBoolean(3, entity.isPromotional());
            creatPs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product read(int id) {
        Product product = null;
        try (Connection connection = DaoDataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            product = new Product(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> readAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DaoDataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                products.add(new Product(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product update(int id, Product newEntity) {
        Product product = null;
        try (Connection connection = DaoDataSource.getConnection();
             PreparedStatement updatePs = connection.prepareStatement(UPDATE_QUERY);
             PreparedStatement selectPs = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            //update row
            updatePs.setString(1, newEntity.getName());
            updatePs.setDouble(2, newEntity.getPrice());
            updatePs.setBoolean(3, newEntity.isPromotional());
            updatePs.setInt(4, id);
            updatePs.executeUpdate();
            //select updated row
            selectPs.setInt(1, id);
            ResultSet resultSet = selectPs.executeQuery();
            resultSet.next();
            product = new Product(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
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
