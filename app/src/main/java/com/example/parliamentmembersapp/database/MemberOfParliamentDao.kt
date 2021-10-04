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
    suspend fun insert(member: MemberOfParliament)

    @Update
    suspend fun update(member: MemberOfParliament)

    @Query("SELECT * FROM members_of_parliament_table WHERE personNumber == :key")
    suspend fun getMember(key: Int) : MemberOfParliament

    @Query("SELECT * FROM members_of_parliament_table ORDER BY first_name ASC")
    fun getAllMembers() : LiveData<List<MemberOfParliament>>

    @Query("DELETE FROM members_of_parliament_table")
    suspend fun clear()

    @Query("SELECT * FROM members_of_parliament_table ORDER BY personNumber DESC LIMIT 1")
    suspend fun getOneMember(): MemberOfParliament?
}