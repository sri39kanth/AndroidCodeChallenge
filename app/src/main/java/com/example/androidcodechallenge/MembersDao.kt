package com.example.androidcodechallenge

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface  MembersDao {
    @Query ("SELECT * FROM membersdata")
    fun getAll() : LiveData<List<MembersData>>

    @Update
    suspend fun updateMember(membersData: MembersData)

    @Insert
    suspend fun insertAll(vararg membersData: MembersData)

}