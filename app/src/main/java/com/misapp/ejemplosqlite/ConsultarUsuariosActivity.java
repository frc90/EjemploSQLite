package com.misapp.ejemplosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.misapp.ejemplosqlite.entities.ConexionSQLiteHelper;
import com.misapp.ejemplosqlite.uilities.Utilidades;

public class ConsultarUsuariosActivity extends AppCompatActivity {

    EditText id, nombre, telefono;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);

        conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        id = findViewById(R.id.documentoId);
        nombre = findViewById(R.id.campoNombreConsulta);
        telefono = findViewById(R.id.campoTelefonoConsulta);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConsultar:
//                consultar();
                consultarSql();
                break;
            case R.id.btnActualizar:
                actualizarUsuarios();
                break;
            case R.id.btnEliminar:
                eliminarUsuario();
                break;
        }
    }

    // eliminar usuario
    private void eliminarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {id.getText().toString()};
        db.delete(Utilidades.USUARIOS, Utilidades.ID + "=?", parametros);
        Toast.makeText(this, "Ya ha borrado el usuario del registro", Toast.LENGTH_SHORT).show();
        limpiar();
        db.close();
    }

    // actualizar usuario
    private void actualizarUsuarios() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {id.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utilidades.NOMBRE, nombre.getText().toString());
        values.put(Utilidades.TELEFONO, telefono.getText().toString());

        db.update(Utilidades.USUARIOS, values, Utilidades.ID + "=?", parametros);
        Toast.makeText(this, "Ya se actualizo el registro", Toast.LENGTH_SHORT).show();
        db.close();
    }

    // sqlite
    private void consultarSql() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {id.getText().toString()};

        try {
            // select nombre, telefono from usuarios where id =?
            String query = "select " + Utilidades.NOMBRE + ", " + Utilidades.TELEFONO + " from " + Utilidades.USUARIOS + " where " + Utilidades.ID + " =?";
            Cursor cursor = db.rawQuery(query, parametros);
            cursor.moveToFirst();

            nombre.setText(cursor.getString(0));
            telefono.setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "El documento no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    // con el Cursor query
    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {id.getText().toString()};
        String[] campos = {Utilidades.NOMBRE, Utilidades.TELEFONO};

        try {
            Cursor cursor = db.query(Utilidades.USUARIOS, campos, Utilidades.ID + "=?", parametros, null, null, null);
            cursor.moveToFirst();
            nombre.setText(cursor.getString(0));
            telefono.setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "El documento no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void limpiar() {
        nombre.setText("");
        telefono.setText("");
    }
}
