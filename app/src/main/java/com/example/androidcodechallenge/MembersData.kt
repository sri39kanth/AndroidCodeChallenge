package com.example.androidcodechallenge

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "membersdata")
data class MembersData(
        @PrimaryKey(autoGenerate = true) val uid: Int?,
        @ColumnInfo(name = "gender") val gender: String?,
        @ColumnInfo(name = "title") val title: String?,
        @ColumnInfo(name = "firstName") val firstName: String?,
        @ColumnInfo(name = "lastName") val lastName: String?,
        @ColumnInfo(name = "city") val city: String?,
        @ColumnInfo(name = "state") val state: String?,
        @ColumnInfo(name = "country") val country: String?,
        @ColumnInfo(name = "postcode") val postcode: String?,
        @ColumnInfo(name = "phone") val phone: String?,
        @ColumnInfo(name = "age") val age: String?,
        @ColumnInfo(name = "large_image") val large: String?,
        @ColumnInfo(name = "medium_image") val medium: String?,
        @ColumnInfo(name = "thumbnail_image") val thumbnail: String?,
@ColumnInfo(name = "status") var status : String?)