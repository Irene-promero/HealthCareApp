package com.irene.apphealthcare

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Formulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)
        back()
        save()
    }

    private fun back() {
        findViewById<ImageButton>(R.id.back_btn).setOnClickListener {
            val menuIntent = Intent(this, MainActivity::class.java)
            startActivity(menuIntent)
        }
    }

    private val db = FirebaseFirestore.getInstance()


    private fun save() {
        val name = findViewById<EditText>(R.id.nombre_p).text.toString()
        findViewById<ImageButton>(R.id.save_btn).setOnClickListener {
            showAlert()
            db.collection("Pacientes")
                .document(findViewById<EditText>(R.id.nombre_p).text.toString()).set(
                    hashMapOf(
                        "nombre" to findViewById<EditText>(R.id.nombre_p).text.toString(),
                        "edad" to findViewById<EditText>(R.id.edad_p).text.toString(),
                        "sexo" to findViewById<EditText>(R.id.genero_p).text.toString(),
                        "dependencia" to findViewById<EditText>(R.id.dependencia_p).text.toString()
                    )
                )
        }


    }

    fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Paciente dado de alta")
        builder.setPositiveButton("Aceptar") { dialog: DialogInterface?, which: Int -> salir() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun salir() {
        finish()
    }
}


