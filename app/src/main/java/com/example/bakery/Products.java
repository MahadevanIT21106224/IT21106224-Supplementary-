package com.example.bakery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Vector;

public class Products extends AppCompatActivity {

    EditText EditTextProductId,EditTextProductName,EditTextPrice,EditTextQuantity;
    Button ButtonInsertProduct;
    Spinner SpinnerCategoryId;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        EditTextProductId=(EditText)findViewById(R.id.txtProduct_Id);
        EditTextProductName=(EditText)findViewById(R.id.txtProduct_Name);
        EditTextPrice=(EditText)findViewById(R.id.txtProduct_Price);
        EditTextQuantity=(EditText)findViewById(R.id.txtProduct_Quantity);

        ButtonInsertProduct=(Button)findViewById(R.id.btnProduct_Insert);

        SpinnerCategoryId=(Spinner)findViewById(R.id.spProduct);

        Vector<String> vecCategory= dbHelper.getCategory_Name();

        ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_spinner_item,vecCategory);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerCategoryId.setAdapter(ad);

        ButtonInsertProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EditTextProductId.getText().toString().isEmpty() || EditTextProductName.getText().toString().isEmpty() || EditTextPrice.getText().toString().isEmpty() || EditTextQuantity.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please Enter to all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String CategoryId= dbHelper.getCategory_Id(SpinnerCategoryId.getSelectedItem().toString());

                    Product_class product_class=new Product_class(EditTextProductId.getText().toString(), EditTextProductName.getText().toString(),CategoryId,Integer.parseInt(EditTextPrice.getText().toString()),Integer.parseInt(EditTextQuantity.getText().toString()));

                    if(dbHelper.InsertProduct(product_class))
                    {
                        Toast.makeText(getApplicationContext(),"Product Inserted",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Product is not inserted",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}