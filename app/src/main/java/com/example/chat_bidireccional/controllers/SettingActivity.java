package com.example.chat_bidireccional.controllers;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chat_bidireccional.R;
import com.example.chat_bidireccional.fragments.SettingsFragment;

public class SettingActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //Se reemplaza el constraintLayout de activitysettings por nuestros ajustes
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, new SettingsFragment()).commit();

        //Se activa la opcion "<--" en el menu
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }
    //Se da funcionalidad al boton de volver con una funcion propia de android
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
