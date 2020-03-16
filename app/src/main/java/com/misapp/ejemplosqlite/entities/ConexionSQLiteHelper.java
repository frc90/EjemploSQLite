package com.misapp.ejemplosqlite.entities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.misapp.ejemplosqlite.uilities.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    Utilidades utilidades;

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // crear la tabla usuario
        db.execSQL(utilidades.CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // borrar la db si existe
        db.execSQL(utilidades.BORRAR_TABLA_USUARIO);
        onCreate(db);
    }
}
