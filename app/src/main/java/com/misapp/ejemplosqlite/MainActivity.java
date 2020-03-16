package com.misapp.ejemplosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.misapp.ejemplosqlite.entities.ConexionSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
    }

    public void onCLick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_register:
                intent = new Intent(MainActivity.this, RegistrarUsuariosActivity.class);
                break;
            case R.id.btn_list:
                intent = new Intent(MainActivity.this, ConsultarUsuariosActivity.class);
                break;
            case R.id.btn_list_spinner:
                intent = new Intent(MainActivity.this, ConsultarComboActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
