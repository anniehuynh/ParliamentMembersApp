package com.example.parliamentmembersapp.repository

/**
 * Repository which acts as the intermediary between database and viewmodel
 * All the functions named according to what they will do
 * by An Huynh
 * on 4/10/2021
 */
import androidx.lifecycle.LiveData
import com.example.parliamentmembersapp.database.Comment
import com.example.parliamentmembersapp.database.MemberDatabase
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.database.Rating

class MemberRepository {

    private val memberDao = MemberDatabase.getInstance().memberDatabaseDao

    fun insert(member: MemberOfParliament) = memberDao.insert(member)

    suspend fun clearRating() = memberDao.clearRating()

    suspend fun clearComment() = memberDao.clearComment()

    suspend fun insertRating(rate: Rating) = memberDao.insertRating(rate)

    suspend fun insertComment(comment: Comment) = memberDao.insertComment(comment)

    suspend fun getMemberFromDatabase() = memberDao.getOneMember()

    fun getAllMember(): LiveData<List<MemberOfParliament>> = memberDao.getAllMembers()

    fun getMemberDetails(personNumber: Int) = memberDao.getMemberDetails(personNumber)

    fun getRating(personNumber: Int) = memberDao.getMembersRating(personNumber)

    fun getComment(personNumber: Int) = memberDao.getComment(personNumber)




}


