package com.irene.apphealthcare

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class MainAdapter(private val context : Context): RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    private var dataList = mutableListOf<Usuario>()

    fun setListData (data:MutableList<Usuario>){
        dataList = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row, parent , false)
        return MainViewHolder(view)

    }

    override fun getItemCount(): Int {
        return if(dataList.size >0 ) {
            return dataList.size
        }else {
            0
        }
    }



    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user : Usuario = dataList [position]
        holder.bindView(user)
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("Pacientes").document(user.nombre.toString())
        val btn = holder.itemView.findViewById<ImageButton>(R.id.btn_delete)
        val btn1 = holder.itemView.findViewById<ImageButton>(R.id.btn_edit)
        btn.setOnClickListener{
            showAlert()
            val positionToDelete = holder.adapterPosition
            dataList.removeAt(positionToDelete)
            notifyItemRemoved(positionToDelete)
            docRef.delete()
                .addOnSuccessListener{
                }
        }
        btn1.setOnClickListener {
            val edadTexto = holder.itemView.findViewById<EditText>(R.id.Edad_Paciente).text.toString()
            val edad = edadTexto.toIntOrNull()
            if (edadTexto.isBlank() || edad == null || edad !in 0..99) {
                showAlert1()
           }else {
               if(holder.itemView.findViewById<EditText>(R.id.Sexo_Paciente).text.toString().isNotEmpty()){
                if(holder.itemView.findViewById<EditText>(R.id.Dependencia_Paciente).text.toString().isNotEmpty()){
                    docRef.set(hashMapOf(
                        "nombre" to holder.itemView.findViewById<TextView>(R.id.Nombre_Paciente).text.toString(),
                        "edad" to holder.itemView.findViewById<EditText>(R.id.Edad_Paciente).text.toString(),
                        "sexo" to holder.itemView.findViewById<EditText>(R.id.Sexo_Paciente).text.toString(),
                        "dependencia" to holder.itemView.findViewById<EditText>(R.id.Dependencia_Paciente).text.toString()))
                    showAlert4()
                }else { showAlert3() }
            }else { showAlert2() } }
        }


    }

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindView (user: Usuario) {
            itemView.findViewById<TextView>(R.id.Nombre_Paciente).text = user.nombre
            itemView.findViewById<TextView>(R.id.Edad_Paciente).text = user.edad
            itemView.findViewById<TextView>(R.id.Sexo_Paciente).text = user.sexo
            itemView.findViewById<TextView>(R.id.Dependencia_Paciente).text = user.dependencia


        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Paciente borrado con exito")
        builder.setPositiveButton("Aceptar") { dialog: DialogInterface?, which: Int -> salir() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun salir() {
        MainActivity()
    }
    private fun showAlert1() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Pro favor, introduce la edad del paciente")
        builder.setPositiveButton("Aceptar") { dialog: DialogInterface?, which: Int -> salir() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showAlert2() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Por favor, introduce el género del paciente")
        builder.setPositiveButton("Aceptar") { dialog: DialogInterface?, which: Int -> salir() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showAlert3() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Por favor, introduzca el nivel de dependencia del paciente")
        builder.setPositiveButton("Aceptar") { dialog: DialogInterface?, which: Int -> salir() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showAlert4() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Paciente modificado con exito")
        builder.setPositiveButton("Aceptar") { dialog: DialogInterface?, which: Int -> salir() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



}