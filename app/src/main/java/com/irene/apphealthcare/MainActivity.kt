package com.irene.apphealthcare

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


enum class ProviderType {
    BASIC
}


class MainActivity : AppCompatActivity () {

    private lateinit var adapter:MainAdapter
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter(this)

        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager (this)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
        observeData()
    }
    fun observeData (){
        viewModel.fetchUserData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}

