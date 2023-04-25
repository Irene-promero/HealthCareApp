package com.irene.apphealthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth



class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        //Setup
        setup()
        acceso()
    }
    private fun acceso (){
        findViewById<Button>(R.id.button2).setOnClickListener {
            val menuIntent = Intent(this,MainActivity::class.java)
            startActivity(menuIntent)
        }
    }

    private fun setup(){
        findViewById<Button>(R.id.btn_registrar).setOnClickListener {
            if (findViewById<EditText>(R.id.txtedit_email).text.isNotEmpty() && findViewById<EditText>(R.id.txtedit_pass).text.isNotEmpty()
            ) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    findViewById<EditText>(R.id.txtedit_email).text.toString(),
                    findViewById<EditText>(R.id.txtedit_pass).text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showMenu(it.result?.user?.email?:"", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }

            }
        }
        findViewById<Button>(R.id.btn_acceder).setOnClickListener {
            if (findViewById<EditText>(R.id.txtedit_email).text.isNotEmpty() && findViewById<EditText>(
                    R.id.txtedit_pass
                ).text.isNotEmpty()
            ) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    findViewById<EditText>(R.id.txtedit_email).text.toString(),
                    findViewById<EditText>(R.id.txtedit_pass).text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showMenu(it.result?.user?.email?:"", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }

            }
        }
    }
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error en la autentificación al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showMenu(email:String, provider: ProviderType){
        val menuIntent = Intent(this,MainActivity::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(menuIntent)

    }


}
