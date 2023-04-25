package com.irene.apphealthcare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class Repo {
    fun getUserData():LiveData<MutableList<Usuario>>{
       val mutableData = MutableLiveData<MutableList<Usuario>>()
        FirebaseFirestore.getInstance().collection("Usuarios").get().addOnSuccessListener {resultado ->
            val listData:MutableList<Usuario> = mutableListOf<Usuario>()
            for(document in resultado){
                val nombre = document.getString("nombre")
                val edad = document.getString("edad")
                val dependencia = document.getString("dependencia")
                val sexo = document.getString("sexo")
                val usuario = Usuario(nombre!!,edad!!,sexo!!,dependencia!!)
                listData.add(usuario)
            }
            mutableData.value = listData
        }
        return mutableData
    }
}