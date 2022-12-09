package com.example.bakery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Customer extends AppCompatActivity {

    Button ButtonBuy, ButtonSee, ButtonLogout;

    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        ButtonBuy = (Button) findViewById(R.id.btn_buy);
        ButtonSee = (Button) findViewById(R.id.btn_Seeproduct);
        ButtonLogout = (Button) findViewById(R.id.btnC_logout);

        ButtonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBuy = new Intent(Customer.this, Buy.class);
                startActivity(intentBuy);
            }
        });


        ButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogout = new Intent(Customer.this, MainActivity.class);
                startActivity(intentLogout);
            }
        });

        ButtonSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor t= dbHelper.getinfo();
                if (t.getCount()==0){
                    Toast.makeText(Customer.this,"No Product yet", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer=new StringBuffer();
                while (t.moveToNext()){
                    buffer.append("Product name: " +t.getString(1)+"\n");
                    buffer.append("Price: " +t.getString(3)+"\n");
                    buffer.append("Quantity: " +t.getString(4)+"\n\n\n");
                }
                AlertDialog.Builder builder= new AlertDialog.Builder(Customer.this);
                builder.setCancelable(true);
                builder.setTitle("Product List");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


    }

}