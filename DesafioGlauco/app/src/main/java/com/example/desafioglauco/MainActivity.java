package com.example.desafioglauco;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private Button buttonUpload;
    private Button buttonSubmit;

    private EditText txtLogin;
    private EditText txtPass;

    private String TAG_WRITE_READ_FILE = "TAG_WRITE_READ_FILE";

    private String filename = "teste.txt";
    private ArrayList<String> passwords = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        buttonSubmit = findViewById(R.id.submitButton);
        buttonUpload = findViewById(R.id.buttonUploadFile);

        txtLogin = findViewById(R.id.login);
        txtPass = findViewById(R.id.password);


        uploadFileAndSetString();
        verifyUser();
    }


    public void uploadFileAndSetString() {
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileIn = openFileInput(filename);
                    InputStreamReader InputRead = new InputStreamReader(fileIn);

                    String fileText = readFromFileInputStream(fileIn);

                    passwords.add(fileText);

                    for (String str : fileText.split("\n")) {
                        passwords.add(str);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void verifyUser() {

        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String strLogin = txtLogin.getText().toString();
                String strPass = txtPass.getText().toString();

                if (findUser(strLogin, strPass)) {
                    Toast.makeText(MainActivity.this, "Bem vindo!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Login ou Senha incorreta.", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    public Boolean findUser(String user, String password) {
        for (String userdb : passwords) {
            String[] arrStr = userdb.split(";");
            String currentLogin = arrStr[0];
            String currentPass = arrStr[1];
            if (currentLogin.equals(user) && currentPass.equals(password)) {
                return true;
            }
        }

        return false;
    }


    private String readFromFileInputStream(FileInputStream fileInputStream) {
        StringBuffer retBuf = new StringBuffer();

        try {
            if (fileInputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String lineData = bufferedReader.readLine();
                while (lineData != null) {
                    retBuf.append(lineData + "\n");
                    lineData = bufferedReader.readLine();
                }
            }
        } catch (IOException ex) {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        } finally {
            return retBuf.toString();
        }
    }


}
