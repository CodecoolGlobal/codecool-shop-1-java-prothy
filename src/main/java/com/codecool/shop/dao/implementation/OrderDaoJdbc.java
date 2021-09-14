package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.cart.Cart;

import java.sql.*;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {
    Connection connection;

    public OrderDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Order order) {
        int orderId = order.getOrderId();
        int userId = order.getUserId();
        String orderStatus = order.getOrderStatus();

        try {
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO orders (order_id, user_id, order_date, order_status)
                    VALUES(?, ?, CURRENT_TIMESTAMP, ?)
                    """);

            statement.setInt(1, orderId);
            statement.setInt(2, userId);
            statement.setString(3, orderStatus);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Order find(Order order) {
        Order orderResult = null;

        int orderId = order.getOrderId();
        int userId = order.getUserId();
        Timestamp orderDate = order.getOrderDate();
        String orderStatus = order.getOrderStatus();

        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM orders
                    WHERE order_id = ? OR user_id = ? OR order_date = ? OR order_status = ?
                    """);

            statement.setInt(1, orderId);
            statement.setInt(2, userId);
            statement.setTimestamp(3, orderDate);
            statement.setString(4, orderStatus);

            ResultSet results = statement.executeQuery();
            results.next();

            orderResult = new Order(
                    results.getInt(0),
                    results.getInt(1),
                    results.getTimestamp(2),
                    results.getString(3));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return orderResult;
    }

    public void remove(Order order) {
        this.remove(order.getOrderId());
    }

    @Override
    public void remove(int orderId) {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM orders
                    WHERE order_id = ?
                    """);

            statement.setInt(1, orderId);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
