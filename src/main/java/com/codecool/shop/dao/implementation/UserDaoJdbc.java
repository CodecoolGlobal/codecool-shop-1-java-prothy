package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.cart.Cart;
import com.codecool.shop.model.user.Admin;
import com.codecool.shop.model.user.Customer;
import com.codecool.shop.model.user.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;

public class UserDaoJdbc implements UserDao {
    private DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "INSERT INTO users(user_name, email, password, isadmin, address, wallet, currency) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.isAdmin());
            if (user instanceof Customer) {
                statement.setString(4, ((Customer) user).getAddress());
                statement.setBigDecimal(5, ((Customer) user).getWallet());
                statement.setString(6, ((Customer) user).getDefaultCurrencyString());
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

    }

    @Override
    public User find(int id) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                return null;
            }
            if (resultSet.getBoolean(4)) {
                User admin = new Admin(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5));
            } else {
                User customer = new Customer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5),
                        new Cart(), // [TODO]: CartJdbc needed
                        new HashSet<>(), // [TODO]: OrderJdbc needed
                        resultSet.getBigDecimal(7),
                        resultSet.getString(8),
                        resultSet.getString(9));
            }

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return null;
    }

    @Override
    public void remove(int id) {

    }
}