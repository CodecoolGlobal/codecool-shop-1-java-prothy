package com.codecool.shop.model.products;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;
import java.util.Currency;

public abstract class SubscriptionProduct extends Product {
    private BigDecimal yearlyPrice;
    private BigDecimal monthlyPrice;

    public SubscriptionProduct(Currency defaultCurrency, String description, ProductCategory productCategory, Supplier supplier, String image, BigDecimal price, BigDecimal yearlyPrice, BigDecimal monthlyPrice) {
        super(defaultCurrency, description, productCategory, supplier, image, price);
        this.yearlyPrice = yearlyPrice;
        this.monthlyPrice = monthlyPrice;
    }
}
