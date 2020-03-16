package com.misapp.ejemplosqlite.uilities;

public class Utilidades {

    public static final String USUARIOS = "usuarios";
    public static final String ID = "usuarios";
    public static final String NOMBRE = "nombre";
    public static final String TELEFONO = "telefono";

    public static final String CREAR_TABLA_USUARIO = "create table " + USUARIOS + " " +
            "(" + ID + " INTIGER, " + NOMBRE + " TEXT, " + TELEFONO + " TEXT)";
    public static final String BORRAR_TABLA_USUARIO = "drop table if exists " + USUARIOS;
}
