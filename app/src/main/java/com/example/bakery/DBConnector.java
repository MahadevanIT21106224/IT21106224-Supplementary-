package com.example.bakery;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnector extends SQLiteOpenHelper{
    public DBConnector(Context context)
    {
        super(context, "Sprinkles_Bakery",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table UserDetails (UserID VARCHAR PRIMARY KEY NOT NULL,Password VARCHAR, UserType VARCHAR)");
        db.execSQL("create table CategoryList (CategoryID VARCHAR PRIMARY KEY NOT NULL, CategoryName VARCHAR)");
        db.execSQL("create table ProductList (ProductID VARCHAR PRIMARY KEY NOT NULL, ProductName VARCHAR, CategoryID VARCHAR, Price INTEGER, Quantity INTEGER, FOREIGN KEY (CategoryID) REFERENCES CategoryList(CategoryID))");
        db.execSQL("create table InvoiceList (InvoiceID VARCHAR PRIMARY KEY NOT NULL,ProductID VARCHAR, ProductName VARCHAR, Price INTEGER, Quantity INTEGER, Total INTEGER, FOREIGN KEY (ProductID) REFERENCES ProductList(ProductID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
