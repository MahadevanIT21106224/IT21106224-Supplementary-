package com.example.bakery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Buy extends AppCompatActivity {

    EditText EditTextInvoiceId, EditTextProductId, EditTextProductName, EditTextPrice, EditTextCategory, EditTextQuantity,EditTextBuyQuantity, EditTextTotal;
    Button ButtonSearchProduct, ButtonBuyProduct;


    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        EditTextInvoiceId=(EditText)findViewById(R.id.txtI_Invoiceid);
        EditTextProductId=(EditText)findViewById(R.id.txtI_Productid);
        EditTextProductName=(EditText)findViewById(R.id.txtI_ProductName);
        EditTextPrice=(EditText)findViewById(R.id.txtI_Productprice);
        EditTextCategory=(EditText)findViewById(R.id.txtI_Categoryid);
        EditTextQuantity=(EditText)findViewById(R.id.txtI_Productqty);
        EditTextBuyQuantity=(EditText)findViewById(R.id.txtI_Needbuy);
        EditTextTotal=(EditText)findViewById(R.id.txtI_Total);

        ButtonSearchProduct= (Button)findViewById(R.id.btn_search);
        ButtonBuyProduct= (Button)findViewById(R.id.btn_purches);

        ButtonSearchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PName=EditTextProductName.getText().toString();

                ArrayList<Product_class> Product_classList=dbHelper.SearchProduct(PName);

                if(Product_classList.size()!=0)
                {
                    Product_class product_class= Product_classList.get(0);
                    EditTextProductId.setText(product_class.getProductID());
                    EditTextQuantity.setText((String.valueOf(product_class.getQuantity())));
                    EditTextPrice.setText((String.valueOf(product_class.getPrice())));
                    EditTextCategory.setText(product_class.getCategoryID());

                    Toast.makeText(getApplicationContext(),"Item found successfully", Toast.LENGTH_LONG).show();


                }
                else
                {
                    Toast.makeText(getApplicationContext(), "We haven't this item", Toast.LENGTH_LONG).show();
                }
            }
        });

        ButtonBuyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = Integer.parseInt(EditTextBuyQuantity.getText().toString())* Integer.parseInt(EditTextPrice.getText().toString());

                dbHelper.Buyproduct(EditTextProductId.getText().toString(),Integer.parseInt(EditTextBuyQuantity.getText().toString()));

                EditTextTotal.setText(String.valueOf(total));

                Buy_class buy_class=new Buy_class(EditTextInvoiceId.getText().toString(), EditTextProductId.getText().toString(),Integer.parseInt(EditTextPrice.getText().toString()),Integer.parseInt(EditTextBuyQuantity.getText().toString()),total);

                if(dbHelper.InsertInvoice(buy_class))
                {
                    Toast.makeText(getApplicationContext(),"Thank you",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please try again",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}