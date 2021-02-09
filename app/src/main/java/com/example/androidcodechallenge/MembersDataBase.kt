package com.example.androidcodechallenge

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(MembersData::class), version = 3)
abstract class MembersDataBase : RoomDatabase() {
    abstract fun membersDao() : MembersDao

    companion object {
        private lateinit var INSTANCE: MembersDataBase

        fun getInstance(context : Context) : MembersDataBase {

            if(this::INSTANCE.isInitialized)  { return INSTANCE }

            synchronized(MembersDataBase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                MembersDataBase::class.java,
                "members_data_base")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }


        }
    }
}