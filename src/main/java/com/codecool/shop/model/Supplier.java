package com.codecool.shop.model;

import com.codecool.shop.model.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Supplier extends BaseModel {
    private List<Product> products;

    public Supplier(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name
        );
    }
}