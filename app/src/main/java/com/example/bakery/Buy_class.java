package com.example.bakery;

public class Buy_class {
    private String InvoiceID;
    private String ProductID;
    private int Price;
    private int Quantity;
    private int Total;

    public Buy_class() {

    }

    public Buy_class(String invoiceID, String productID, int price, int quantity, int total) {
        InvoiceID = invoiceID;
        ProductID = productID;
        Price = price;
        Quantity = quantity;
        Total = total;

    }



    public String getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        InvoiceID = invoiceID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
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

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }
}
