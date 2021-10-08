package com.example.parliamentmembersapp.repository

/**
 * Repository
 * by An Huynh
 * on 4/10/2021
 */
import androidx.lifecycle.LiveData
import com.example.parliamentmembersapp.database.MemberDatabase
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.database.MemberOfParliamentDao

class MemberRepository {
    val memberDao = MemberDatabase.getInstance().memberDatabaseDao

    fun getAll(): LiveData<List<MemberOfParliament>> = memberDao.getAllMembers()

    fun insert(member:MemberOfParliament) = memberDao.insert(member)

    fun getMemberDetails(personNumber: Int) = memberDao.getMemberDetails(personNumber)

    //fun getRating(personNumber: Int) = memberDao.getMembersRating(personNumber)


}


