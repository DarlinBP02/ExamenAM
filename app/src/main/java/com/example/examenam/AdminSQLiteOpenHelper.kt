package com.example.examenam

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory

class AdminSQLiteOpenHelper(context: Context, name: String, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        //db.execSQL("DROP TABLE IF EXISTS empleados")
        db.execSQL("CREATE TABLE empleados(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, cargo TEXT, edad INTEGER, telefono INTEGER,cedula INTEGER, direccion TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}