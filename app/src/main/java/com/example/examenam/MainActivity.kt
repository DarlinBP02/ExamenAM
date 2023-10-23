package com.example.examenam

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

data class Empleados(
    val id: Int,
    val nombre: String,
    val cargo: String,
    val edad: Int,
    val telefono: Int,
    val cedula: Int,
    val direccion: String


){
    override fun toString(): String {
        return "Id: $id, Nombre: $nombre, Cardo: $cargo, Edad: $edad, Direccion: $$telefono, Cedula: \$$cedula,"
    }
}

class MainActivity : AppCompatActivity() {
    val items = mutableListOf<Empleados>()

    lateinit var et1: EditText
    lateinit var etNombre: EditText
    lateinit var etcargo: EditText
    lateinit var etedad: EditText
    lateinit var ettelefono: EditText
    lateinit var etcedula: EditText
    lateinit var etdireccion: EditText

    lateinit var boton1: Button
    lateinit var botonVerLista: Button
    lateinit var botonBuscarCodigo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        et1 = findViewById(R.id.et1)
        etNombre = findViewById(R.id.etNombre)
        etcargo = findViewById(R.id.etcargo)
        etedad = findViewById(R.id.etedad)
        ettelefono = findViewById(R.id.ettelefono)
        etcedula = findViewById(R.id.etcedula)
        etdireccion = findViewById(R.id.etdireccion)

        boton1 = findViewById(R.id.boton1)

        botonVerLista = findViewById(R.id.botonVerLista)
        botonBuscarCodigo = findViewById(R.id.botonBuscarCodigo)


        //Crear empleados
        boton1.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("nombre", etNombre.text.toString())
            registro.put("cargo", etcargo.text.toString())
            registro.put("edad", etedad.text.toString().toInt())
            registro.put("telefono", ettelefono.text.toString().toInt())
            registro.put("cedula", etcedula.text.toString().toInt())
            registro.put("direccion", etdireccion.text.toString())

            val check = bd.insert("empleados", null, registro)
            if (check != -1L) {
                showToast("Nuevo empleado creado")
            } else {
                showToast("Error al crear el empleado")
            }
            bd.close()
            clearEditTexts()

        }

        botonVerLista.setOnClickListener {
            val intent = Intent(this, ListaEmpleadosActivity::class.java)
            startActivity(intent)
        }


        botonBuscarCodigo.setOnClickListener {
            val intent = Intent(this, BusquedaCodigoActivity::class.java)
            startActivity(intent)
        }

    }






    private fun clearEditTexts(){
        et1.setText("")
        etNombre.setText("")
        etcargo.setText("")
        etedad.setText("")
        ettelefono.setText("")
        etcedula.setText("")
        etdireccion.setText("")
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
