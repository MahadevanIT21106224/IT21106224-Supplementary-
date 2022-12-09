package com.example.bakery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Categories extends AppCompatActivity {

    EditText EditTextCategoryId,EditTextCategoryName;
    Button ButtonInsertCategory;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        EditTextCategoryId=(EditText)findViewById(R.id.txtCategory_Id);
        EditTextCategoryName=(EditText)findViewById(R.id.txtCategory_Name);

        ButtonInsertCategory=(Button)findViewById(R.id.btnCategory_Insert);

        ButtonInsertCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(EditTextCategoryId.getText().toString().isEmpty() || EditTextCategoryName.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Enter to all the fields",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Category_class category_class=new Category_class(EditTextCategoryId.getText().toString(),EditTextCategoryName.getText().toString());
                    if(dbHelper.InsertCategory(category_class))
                    {
                        Toast.makeText(getApplicationContext(),"Category inserted", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Category is not inserted",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
}