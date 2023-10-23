package com.example.examenam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class ListaEmpleadosActivity : AppCompatActivity() {
    val items = mutableListOf<Empleados>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_empleados)

        getAllItems()
    }

    private fun getAllItems(){
        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("select * from empleados", null)

        items.clear()
        while (fila.moveToNext()) {
            val empleados = Empleados(
                fila.getString(0).toInt(),
                fila.getString(1),
                fila.getString(2),
                fila.getString(3).toInt(),
                fila.getString(4).toInt(),
                fila.getString(5).toInt(),
                fila.getString(6)
            )
            items.add(empleados)
        }
        bd.close()

        updateListView()
    }

    private fun deleteItem(id: Int) {
        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val bd = admin.writableDatabase

        println("Intentando eliminar el empleado con ID: $id")

        bd.delete("empleados", "id=?", arrayOf(id.toString()))

        bd.execSQL("UPDATE empleados SET id = id - 1 WHERE id > $id")

        bd.close()

        getAllItems()
    }


    private fun updateListView(){
        val listView2 = findViewById<ListView>(R.id.listView)
        val newAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView2.adapter = newAdapter
        newAdapter.notifyDataSetChanged()

        listView2.setOnItemClickListener { _, _, position, _ ->
            val empleados = items[position]
            deleteItem(empleados.id)
        }
    }
}
