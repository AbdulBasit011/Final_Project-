package com.example.login_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button Login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Login = findViewById(R.id.button);
        DB = new DBHelper(this);


        Login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         String user = username.getText().toString();
                                         String pass = password.getText().toString();

                                         if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                                             Toast.makeText(MainActivity.this, "All fields are Required", Toast.LENGTH_SHORT).show();
                                         else {
                                             if (pass.equals(pass)) {
                                                 Boolean checkuser = DB.checkusername(user);
                                                 if (checkuser == false) {
                                                     Boolean insert = DB.insertData(user, pass);
                                                     if (insert == true) {
                                                         Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                         Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                                         startActivity(intent);
                                                     } else {
                                                         Toast.makeText(MainActivity.this, "Registratioin Failed", Toast.LENGTH_SHORT).show();
                                                     }
                                                 } else {
                                                     Toast.makeText(MainActivity.this, "User already Exists", Toast.LENGTH_SHORT).show();
                                                 }
                                             } else {
                                                 Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                             }

                                         }


                                     }


                                 }
        );
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class
                );
                startActivity(intent);
            }
        });
    }
}