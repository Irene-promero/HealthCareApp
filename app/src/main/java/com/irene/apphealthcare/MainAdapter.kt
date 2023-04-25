package com.irene.apphealthcare

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView



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
    }

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindView (user: Usuario) {
            itemView.findViewById<TextView>(R.id.Nombre_Paciente).text = user.nombre
            itemView.findViewById<TextView>(R.id.Edad_Paciente).text = user.edad
            itemView.findViewById<TextView>(R.id.Sexo_Paciente).text = user.sexo
            itemView.findViewById<TextView>(R.id.Dependencia_Paciente).text = user.dependencia


        }
    }

}