package com.example.androidcodechallenge

class MembersRepository(private val membersDao: MembersDao) {
    val members = membersDao.getAll()
    suspend fun insert(membersData: MembersData) {
        membersDao.insertAll(membersData)
    }

    suspend fun update(membersData: MembersData) {
        membersDao.updateMember(membersData)
    }
}