package com.irene.apphealthcare

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
import java.lang.System.exit


enum class ProviderType {
    BASIC
}


class MainActivity: AppCompatActivity () {

     lateinit var adapter:MainAdapter
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter(this)

        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager (this)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter

        observeData()
        exit()
        añadir()
        actualizar()

    }
    fun observeData (){
        viewModel.fetchUserData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
    private fun añadir (){
        findViewById<ImageButton>(R.id.btn_add).setOnClickListener {
            val menuIntent = Intent (this, Formulario::class.java)
            startActivity(menuIntent)}
    }
    private fun actualizar (){
        findViewById<ImageButton>(R.id.btn_actualiza).setOnClickListener {
            val menuIntent = Intent (this, MainActivity::class.java)
            startActivity(menuIntent)}
    }
     private fun exit (){
        findViewById<ImageButton>(R.id.exit_btn).setOnClickListener {
        val menuIntent = Intent (this, AuthActivity::class.java)
        startActivity(menuIntent)}
    }
}

