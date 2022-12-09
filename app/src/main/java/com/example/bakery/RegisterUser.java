package com.example.bakery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity {

    EditText EditTextUserName,EditTextPassword,EditTextConfirmPassword;
    Spinner SpinnerUserType;
    Button ButtonRegister;

    private DBHelper dbHelper;

    String UserType[]={"Admin","Customer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        EditTextUserName=(EditText)findViewById(R.id.txtUserName);
        EditTextPassword=(EditText)findViewById(R.id.txtPassword);
        EditTextConfirmPassword=(EditText)findViewById(R.id.txtCon_Password);
        ButtonRegister=(Button)findViewById(R.id.btnRegisterButton);

        SpinnerUserType=(Spinner) findViewById(R.id.spUserType);

        ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_spinner_item,UserType);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerUserType.setAdapter(ad);
        
        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditTextUserName.getText().toString().isEmpty() || EditTextPassword.getText().toString().isEmpty() || EditTextConfirmPassword.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please Enter to all the fields", Toast.LENGTH_SHORT).show();
                }
                else if (EditTextPassword.getText().toString().length()<5)
                {
                    Toast.makeText(getApplicationContext(), "please enter 5 or more character password", Toast.LENGTH_SHORT).show();
                }
                else if (!EditTextPassword.getText().toString().equals(EditTextConfirmPassword.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Confirm password is not match", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    User user=new User(EditTextUserName.getText().toString(),EditTextPassword.getText().toString(),SpinnerUserType.getSelectedItem().toString());
                    if (dbHelper.RegisterUser(user))
                    {
                        Toast.makeText(getApplicationContext(),"Account created successfully",Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),EditTextUserName.getText().toString()+",you can login as "+SpinnerUserType.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Account is not created", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}