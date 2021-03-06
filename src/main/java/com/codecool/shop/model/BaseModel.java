package com.codecool.shop.model;

import com.codecool.shop.dao.DatabaseManager;

import java.lang.reflect.Field;
import java.sql.SQLException;

public class BaseModel {

    private int id;
    private String name;

    public BaseModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BaseModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
                if (value != null) {
                    sb.append(field.getName() + ":" + value + ",");
                }
            } catch (IllegalAccessException e) {

            }
        }
        return sb.toString();
    }
}
