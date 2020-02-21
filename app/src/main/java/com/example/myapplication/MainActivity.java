package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void verifyUser(View v){
        EditText login;
        EditText pass;

        login = findViewById(R.id.login);
        pass= findViewById(R.id.pass);

        String txtLogin = login.getText().toString();
        String txtPass = pass.getText().toString();


        String loginPadrao = "admin";
        String senhaPadrao = "admin123";

        if(loginPadrao.equals(txtLogin) && senhaPadrao.equals(txtPass)){
            Toast.makeText(this, "Bem Vindo!", Toast.LENGTH_LONG).show();
        }else{
        }    Toast.makeText(this, "Login ou Senha incorreta", Toast.LENGTH_LONG).show();


    }
}
