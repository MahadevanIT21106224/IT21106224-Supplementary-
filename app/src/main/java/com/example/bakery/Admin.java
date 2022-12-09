package com.example.bakery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin extends AppCompatActivity {

    Button ButtonCategory, ButtonProduct, ButtonLogout, Button1See;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        ButtonCategory=(Button)findViewById(R.id.btn_category);
        ButtonProduct=(Button)findViewById(R.id.btn_add);
        ButtonLogout=(Button)findViewById(R.id.btnA_logout);
        Button1See=(Button)findViewById(R.id.btnA_see);


        ButtonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCategory= new Intent(Admin.this,Categories.class);
                startActivity(intentCategory);
            }
        });

        ButtonProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProduct= new Intent(Admin.this,Products.class);
                startActivity(intentProduct);
            }
        });

        ButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogout = new Intent(Admin.this,MainActivity.class);
                startActivity(intentLogout);
            }
        });
        Button1See.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor t= dbHelper.getinfo();
                if (t.getCount()==0){
                    Toast.makeText(Admin.this,"No Product yet", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer=new StringBuffer();
                while (t.moveToNext()){
                    buffer.append("Product name: " +t.getString(1)+"\n");
                    buffer.append("Price: " +t.getString(3)+"\n");
                    buffer.append("Quantity: " +t.getString(4)+"\n\n\n");
                }
                AlertDialog.Builder builder= new AlertDialog.Builder(Admin.this);
                builder.setCancelable(true);
                builder.setTitle("Product List");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}