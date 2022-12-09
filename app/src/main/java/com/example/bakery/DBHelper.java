package com.example.bakery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class DBHelper {
    private Context con;
    private SQLiteDatabase db;

    public DBHelper(Context con) {
        this.con = con;
    }

    public DBHelper OpenDB() {
        DBConnector dbCon = new DBConnector(con);
        db = dbCon.getWritableDatabase();
        return this;
    }

    public boolean RegisterUser(User user) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("UserId", user.getUserID());
            cv.put("Password", user.getPassword());
            cv.put("UserType", user.getUserType());

            db.insert("UserDetails", null, cv);
            return true;
        } catch (Exception ex) {
            Toast.makeText(con, ex.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public ArrayList<User> ValidLogin(String UserId,String Password)
    {
        ArrayList<User> userList=new ArrayList<>();
        try
        {
            Cursor cursor=db.rawQuery("Select * from UserDetails where userID='"+UserId+"' and Password = '"+Password+"' ",null);
            if (cursor.moveToFirst())
            {
                do {
                    User user = new User();
                    user.setUserID(cursor.getString(0));
                    user.setPassword(cursor.getString(1));
                    user.setUserType(cursor.getString(2));

                    userList.add(user);
                }while (cursor.moveToNext());

            }
        }
        catch (Exception ex)
        {
            Toast.makeText(con,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
        return userList;

    }

    public boolean InsertCategory(Category_class category_class) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("CategoryId", category_class.getCategoryId());
            cv.put("CategoryName", category_class.getCategoryName());

            db.insert("CategoryList", null, cv);
            return true;
        } catch (Exception ex) {
            Toast.makeText(con, ex.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public Vector<String> getCategory_Name(){
        Vector <String> vecCategory = new Vector<String>();
        try {
            Cursor cursor=db.rawQuery("Select CategoryName from CategoryList",null);
            if (cursor.moveToFirst()){
                do{
                    vecCategory.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(con, ex.getMessage(),Toast.LENGTH_LONG).show();
        }
        return vecCategory;
    }

    public String getCategory_Id(String CategoryName){
        String CategoryId=null;
        try {
            Cursor cursor = db.rawQuery("Select CategoryID from CategoryList where CategoryName= '" + CategoryName + "' ", null);
            if (cursor.moveToFirst()) {

                CategoryId = cursor.getString(0);
            }
        }
        catch (Exception ex)
            {
                Toast.makeText(con, ex.getMessage(),Toast.LENGTH_LONG).show();
            }
        return CategoryId;
    }

    public boolean InsertProduct (Product_class product_class) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("ProductId", product_class.getProductID());
            cv.put("ProductName", product_class.getProductName());
            cv.put("CategoryId", product_class.getCategoryID());
            cv.put("Price", product_class.getPrice());
            cv.put("Quantity", product_class.getQuantity());

            db.insert("ProductList", null, cv);
            return true;
        } catch (Exception ex) {
            Toast.makeText(con, ex.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public ArrayList<Product_class> SearchProduct (String ProductName)
    {
        ArrayList<Product_class> Product_classList=new ArrayList<>();
        try
        {
            Cursor cursor=db.rawQuery("Select * from ProductList where ProductName='"+ProductName+"'",null);
            if (cursor.moveToFirst())
            {
                do {
                    Product_class product_class = new Product_class();
                    product_class.setProductID(cursor.getString(0));
                    product_class.setProductName(cursor.getString(1));
                    product_class.setCategoryID(cursor.getString(2));
                    product_class.setPrice(cursor.getInt(3));
                    product_class.setQuantity(cursor.getInt(4));
                    Product_classList.add(product_class);
                }while (cursor.moveToNext());

            }
        }
        catch (Exception ex)
        {
            Toast.makeText(con,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
        return Product_classList;

    }

    public void Buyproduct (String Product_ID, int Qty)
    {
        try{
            db.execSQL("update ProductList set Quantity=Quantity-"+Qty+" where ProductID= '"+Product_ID+"' ");
            Toast.makeText(con, "Thank you", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(con,"Try again"+ex.getMessage(),Toast.LENGTH_LONG).show();
        }

    }


    public boolean InsertInvoice (Buy_class buy_class){
        try{
            ContentValues cv = new ContentValues();
            cv.put("InvoiceID", buy_class.getInvoiceID());
            cv.put("ProductID", buy_class.getProductID());
            cv.put("Price", buy_class.getPrice());
            cv.put("Quantity", buy_class.getQuantity());
            cv.put("Total", buy_class.getTotal());

            db.insert("InvoiceList", null,cv);
            return true;
        }
        catch (Exception ex){
            Toast.makeText(con,ex.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }

    }

    public Cursor getinfo(){

       Cursor cursor = db.rawQuery("select * from ProductList",null);
       return cursor;
    }





}



