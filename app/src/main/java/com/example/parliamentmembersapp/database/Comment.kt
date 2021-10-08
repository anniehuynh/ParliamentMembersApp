package com.example.parliamentmembersapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class for inserting comment for member
 * by An Huynh
 * on 7/10/2021
 */
@Entity(tableName = "member_comment_table")
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    val personNumber: Int,


    //comment
    @ColumnInfo (name = "comment")
    val comment: String,
)
