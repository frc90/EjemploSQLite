package com.misapp.ejemplosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.misapp.ejemplosqlite.entities.ConexionSQLiteHelper;
import com.misapp.ejemplosqlite.entities.Usuario;
import com.misapp.ejemplosqlite.uilities.Utilidades;

import java.util.ArrayList;

public class ConsultarComboActivity extends AppCompatActivity {

    Spinner comboPersonas;
    TextView id, nombre, telefono;
    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasLista;

    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_combo);

        conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        comboPersonas = findViewById(R.id.comboPersonas);

        id = findViewById(R.id.txtDocumento);
        nombre = findViewById(R.id.txtNombre);
        telefono = findViewById(R.id.txtTelefono);

        consultarListaPersonas();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter
                (this, R.layout.support_simple_spinner_dropdown_item, listaPersonas);
        comboPersonas.setAdapter(adapter);
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario persona = null;
        personasLista = new ArrayList<Usuario>();

        // select * form ususarios
        String query = "select * from " + Utilidades.USUARIOS;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            personasLista.add(persona);
        }

        obtenerLista();
    }

    private void obtenerLista() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");
        for (int i = 0; i < personasLista.size(); i++) {
            listaPersonas.add(personasLista.get(i).getId() + "-" + personasLista.get(i).getNombre());
        }
    }
}
