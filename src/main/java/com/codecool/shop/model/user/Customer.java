package com.codecool.shop.model.user;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Payment;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class Customer extends User{
    private Cart cart;
    private HashSet<Order> orders;
    private BigDecimal wallet;
    private Currency defaultCurrency;
    private Payment payment;

    public Customer(int id, String name, String email, String password, boolean isAdmin, HashSet<Order> orders, BigDecimal wallet, Currency defaultCurrency) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.cart = new Cart();
        this.orders = orders;
        this.wallet = wallet;
        this.defaultCurrency = defaultCurrency;
    }

    public Customer() {
        this.id = 1;
        this.name = "guest";
        this.email = null;
        this.password = null;
        this.isAdmin = false;
        this.cart = new Cart();
        this.orders = new HashSet<>();
        this.wallet = new BigDecimal(42);
        this.defaultCurrency = Currency.getInstance("EUR");
    }


    public void addOrder() {
        orders.add(new Order(1));
    }

    public void cancelOrder(int id) {
        Order chosenOrder = orders.stream()
                .parallel().filter(order -> order.getId() == id)
                .findFirst().orElseThrow(NoSuchElementException::new);
        orders.remove(chosenOrder);
    }

    private boolean confirmOrder() {
        // [TODO]: not implemented
        return true;
    }

    private String choosePayment() {
        // [TODO]: not implemented
        return "";
    }

    private void decreaseWallet() {
        // [TODO]: not implemented
    }

    private void checkPaymentType(String payment) {
        // [TODO]: not implemented
    }

    public HashSet<Order> getOrders() {
        return orders;
    }
}