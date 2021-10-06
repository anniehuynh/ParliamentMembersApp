package com.example.parliamentmembersapp.database

/**
 * Repository
 * by An Huynh
 * on 4/10/2021
 */
import androidx.lifecycle.LiveData
import com.example.parliamentmembersapp.database.MemberDatabase
import com.example.parliamentmembersapp.database.MemberOfParliament

class MemberRepository(private val memberDao: MemberOfParliamentDao) {
    //val memberDao = MemberDatabase.getInstance().memberDatabseDao
    fun getAll(): LiveData<List<MemberOfParliament>> = memberDao.getAllMembers()

    suspend fun insert(member: MemberOfParliament) = memberDao.insert(member)

    companion object {
        @Volatile private var instance: MemberRepository? = null

        fun getInstance(memberDao: MemberOfParliamentDao) =
            instance ?: synchronized(this){
                instance ?: MemberRepository(memberDao).also { instance = it }
            }
    }
}