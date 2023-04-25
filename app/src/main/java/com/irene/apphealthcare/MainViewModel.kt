package com.irene.apphealthcare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel ()  {
    private val repo = Repo()
    fun fetchUserData ():LiveData<MutableList<Usuario>>{
       val mutableData = MutableLiveData<MutableList<Usuario>>()
        repo.getUserData().observeForever{Userlist->
            mutableData.value = Userlist
        }
        return mutableData
    }

}