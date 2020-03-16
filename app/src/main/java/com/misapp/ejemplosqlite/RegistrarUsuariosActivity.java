package com.misapp.ejemplosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.misapp.ejemplosqlite.entities.ConexionSQLiteHelper;
import com.misapp.ejemplosqlite.uilities.Utilidades;

public class RegistrarUsuariosActivity extends AppCompatActivity {

    EditText et_id, et_nombre, et_telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuarios);

        et_id = findViewById(R.id.et_id);
        et_nombre = findViewById(R.id.et_nombre);
        et_telefono = findViewById(R.id.et_telefono);
    }

    public void onClick(View v) {
        registrarUsuarios();
//        registrarUsuariosSql();
    }

    // estructura sqlite
    private void registrarUsuariosSql() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        String insert = "INSERT INTO " + Utilidades.USUARIOS +
                "(" + Utilidades.ID + "," + Utilidades.NOMBRE + ", " + Utilidades.TELEFONO + ") VALUES " +
                "(" + et_id.getText().toString() +
                ",'" + et_nombre.getText().toString() +
                "','" + et_telefono.getText().toString() + "')";

        db.execSQL(insert);
        db.close();
    }

    // usando content values
    private void registrarUsuarios() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.ID, et_id.getText().toString());
        values.put(Utilidades.NOMBRE, et_nombre.getText().toString());
        values.put(Utilidades.TELEFONO, et_telefono.getText().toString());

        Long idResultante = db.insert(Utilidades.USUARIOS, Utilidades.ID, values);
        Toast.makeText(this, "Id registro: " + idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }
}
