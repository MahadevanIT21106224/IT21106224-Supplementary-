package com.example.bakery;

public class Product_class {
    private String ProductID;
    private String ProductName;
    private String CategoryID;
    private int Price;
    private int Quantity;

    public Product_class() {

    }

    public Product_class(String productID, String productName, String categoryID, int price, int quantity) {
        ProductID = productID;
        ProductName = productName;
        CategoryID = categoryID;
        Price = price;
        Quantity = quantity;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
