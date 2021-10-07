package com.example.parliamentmembersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parliamentmembersapp.database.MemberDatabase
import com.example.parliamentmembersapp.network.ParliamentApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Default).launch {
            val db = MemberDatabase.getInstance()
            db.memberDatabaseDao.clearTable()

            val dataSrc = ParliamentApi.retrofitService.getParliamentMembers()
            for (item in dataSrc) {
                db.memberDatabaseDao.insert(item)
            }
        }
    }
}