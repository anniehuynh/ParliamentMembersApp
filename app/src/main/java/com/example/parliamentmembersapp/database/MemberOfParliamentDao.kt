package com.example.parliamentmembersapp.database

/**
 * DatabaseDAO
 * An Huynh
 * created on 29/9/2021
 */
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemberOfParliamentDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(member: MemberOfParliament)

    @Query("SELECT * FROM members_of_parliament_table WHERE personNumber = :personNumber")
    fun getMemberDetails(personNumber: Int) : LiveData<MemberOfParliament>

    @Query("SELECT * FROM members_of_parliament_table ORDER BY first_name ASC")
    fun getAllMembers() : LiveData<List<MemberOfParliament>>

    @Query("DELETE FROM members_of_parliament_table")
    suspend fun clearTable()

    @Query("SELECT * FROM members_of_parliament_table ORDER BY personNumber DESC LIMIT 1")
    suspend fun getOneMember(): MemberOfParliament?

    @Insert
    suspend fun insertComment(comment: Comment)

    @Query("SELECT * FROM member_comment_table WHERE personNumber = :personNumber")
    fun getComment(personNumber: Int): LiveData<List<Comment>>

    @Insert
    suspend fun insertRating(rate: Rating)

    @Query("SELECT member_rating FROM member_rating_table WHERE personNumber = :personNumber")
    fun getMembersRating(personNumber: Int): LiveData<List<Double>>

}