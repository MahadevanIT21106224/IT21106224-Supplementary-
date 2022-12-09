package com.example.bakery;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    EditText EditTextUserName,EditTextPassword;
    Button ButtonLogin;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        EditTextUserName=(EditText)findViewById(R.id.txtUserName);
        EditTextPassword=(EditText)findViewById(R.id.txtCon_Password);

        ButtonLogin=(Button)findViewById(R.id.btnLoginbutton);

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<User> userDetails= dbHelper.ValidLogin(EditTextUserName.getText().toString(),EditTextPassword.getText().toString());

                if (userDetails.size()!=0)
                {
                    User user= userDetails.get(0);
                    String UserType=user.getUserType();

                        Toast.makeText(getApplicationContext(), "Welcome" +UserType, Toast.LENGTH_SHORT).show();
                        if (UserType.equals("Admin"))
                        {
                        Intent intentRegister = new Intent(Login.this,Admin.class);
                        startActivity(intentRegister);

                        }
                        else
                        {
                        Intent intentRegister = new Intent(Login.this,Customer.class);
                        startActivity(intentRegister);
                        }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Inserted data invalid", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}