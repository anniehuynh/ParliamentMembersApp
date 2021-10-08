package com.example.parliamentmembersapp.repository

/**
 * Repository
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
    suspend fun insertRating(rate: Rating) = memberDao.insertRating(rate)

    suspend fun insertComment(comment: Comment) = memberDao.insertComment(comment)

    suspend fun getMemberFromDatabase() = memberDao.getOneMember()

    fun getAllMember(): LiveData<List<MemberOfParliament>> = memberDao.getAllMembers()

    fun getMemberDetails(personNumber: Int) = memberDao.getMemberDetails(personNumber)

    fun getRating(personNumber: Int) = memberDao.getMembersRating(personNumber)

    fun getComment(personNumber: Int) = memberDao.getComment(personNumber)




}


