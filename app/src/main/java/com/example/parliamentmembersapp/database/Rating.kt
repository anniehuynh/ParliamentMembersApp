package com.example.parliamentmembersapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class for member's rating
 * by An Huynh
 * on 7/10/2021
 */
@Entity(tableName = "member_rating_table")
data class Rating(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    val personNumber: Int,

    @ColumnInfo(name = "member_rating")
    val rating: Float = 0F,
)
