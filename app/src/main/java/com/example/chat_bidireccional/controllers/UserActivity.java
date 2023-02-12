package com.example.chat_bidireccional.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chat_bidireccional.R;
import com.google.android.material.textfield.TextInputEditText;

public class UserActivity extends AppCompatActivity {

    private TextInputEditText txtUser;
    private Button btAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        txtUser = findViewById(R.id.txtInputUser);
        btAccept = findViewById(R.id.BtAccept);

        btAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = String.valueOf(txtUser.getText());
                if (!user.equals("")){
                    Intent i = new Intent(UserActivity.this, MainActivity.class);
                    i.putExtra("User", user);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Por favor introduzca su nombre de usuario",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
