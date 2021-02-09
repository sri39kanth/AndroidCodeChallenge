package com.example.androidcodechallenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MembersViewModel(application: Application) : AndroidViewModel(application) {
    private  var  membersRepository : MembersRepository
     var members : LiveData<List<MembersData>>

    init {
        val membersData = MembersDataBase.getInstance(application).membersDao()
        membersRepository = MembersRepository(membersData)
        members = membersRepository.members
    }

    fun insert(membersData: MembersData) = viewModelScope.launch {
         membersRepository.insert(membersData)
    }

    fun update(membersData: MembersData) = viewModelScope.launch {
        membersRepository.update(membersData)
    }
}