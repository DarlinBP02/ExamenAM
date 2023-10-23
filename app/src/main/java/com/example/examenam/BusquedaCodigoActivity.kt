package com.example.examenam

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BusquedaCodigoActivity : AppCompatActivity() {

    lateinit var etCodigoBusqueda: EditText
    lateinit var botonBuscar: Button
    lateinit var resultadoBusqueda: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busqueda_codigo)

        etCodigoBusqueda = findViewById(R.id.etCodigoBusqueda)
        botonBuscar = findViewById(R.id.botonBuscar)
        resultadoBusqueda = findViewById(R.id.resultadoBusqueda)

        botonBuscar.setOnClickListener {
            buscarPorCodigo()
        }
    }

    private fun buscarPorCodigo() {
        val codigo = etCodigoBusqueda.text.toString()
        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val bd = admin.writableDatabase

        val fila = bd.rawQuery("select * from empleados where id=?", arrayOf(codigo))

        if (fila.moveToFirst()) {
            val empleados = Empleados(
                fila.getString(0).toInt(),
                fila.getString(1),
                fila.getString(2),
                fila.getString(3).toInt(),
                fila.getString(4).toInt(),
                fila.getString(5).toInt(),
                fila.getString(6)
            )
            resultadoBusqueda.text = empleados.toString()
        } else {
            resultadoBusqueda.text = "No existe el empleado con dicho código"
        }

        bd.close()
    }
}
