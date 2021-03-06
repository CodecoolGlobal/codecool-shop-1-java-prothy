package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Util;
import com.codecool.shop.model.products.*;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Product product) {
        product.setId(data.size() + 1);
        data.add(product);
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }

    public Product createObjectFromJson(String jsonText) {
        List<List<Product>> products = new ArrayList<>();
        products.add(getJsonOfCloud(jsonText));
        products.add(getJsonOfOs(jsonText));
        products.add(getJsonOfIDE(jsonText));
        products.add(getJsonOfWorkTool(jsonText));

        for (List<Product> productList : products) {
            if (productList.size() > 0) return productList.get(0);
        }
        return null;
    }

    private List<Product> getJsonOfCloud(String jsonString) {
        //Deserialize
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson customGson = gsonBuilder.create();

        Cloud[] cloudProducts = customGson.fromJson(jsonString, Cloud[].class);
        List<Product> finalCloudProducts = new ArrayList<>(Arrays.asList(cloudProducts));

        return finalCloudProducts.stream()
                .filter(element -> element.getProductCategory().getName().equals("Cloud"))
                .collect(Collectors.toList());
    }

    private List<Product> getJsonOfOs(String jsonString) {
        //Deserialize
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson customGson = gsonBuilder.create();

        OS[] OSProducts = customGson.fromJson(jsonString, OS[].class);
        List<Product> finalOSProducts = new ArrayList<>(Arrays.asList(OSProducts));
        finalOSProducts.removeAll(Collections.singleton(null));

        return finalOSProducts.stream()
                .filter(element -> element.getProductCategory().getName().equals("OS"))
                .collect(Collectors.toList());
    }
//
    private List<Product> getJsonOfIDE(String jsonString) {
        //Deserialize
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson customGson = gsonBuilder.create();


        IDE[] IDEProducts = customGson.fromJson(jsonString, IDE[].class);
        List<Product> finalIDEProducts = new ArrayList<>(Arrays.asList(IDEProducts));
        finalIDEProducts.removeAll(Collections.singleton(null));

        return finalIDEProducts.stream()
                .filter(element -> element.getProductCategory().getName().equals("IDE"))
                .collect(Collectors.toList());
    }

    private List<Product> getJsonOfWorkTool(String jsonString) {
        //Deserialize
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson customGson = gsonBuilder.create();


        WorkTool[] WorkToolProducts = customGson.fromJson(jsonString, WorkTool[].class);
        List<Product> finalWorkToolProducts = new ArrayList<>(Arrays.asList(WorkToolProducts));
        finalWorkToolProducts.removeAll(Collections.singleton(null));

        return finalWorkToolProducts.stream()
                .filter(element -> element.getProductCategory().getName().equals("WorkTool"))
                .collect(Collectors.toList());
    }
}
