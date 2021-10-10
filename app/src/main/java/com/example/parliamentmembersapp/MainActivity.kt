package com.example.parliamentmembersapp

/**
 * Main Activity that clear all comments and rating from previously launch version
 * and insert member list
 * by An Huynh
 * on 16/9/2021
 */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parliamentmembersapp.network.ParliamentApi
import com.example.parliamentmembersapp.repository.MemberRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Default).launch {
            val repository = MemberRepository()
            //clear all rating and comment existed from the previously run app
            repository.clearComment()
            repository.clearRating()

            val dataSrc = ParliamentApi.retrofitService.getParliamentMembers()
            for (item in dataSrc) {
                repository.insert(item)
            }
        }
    }
}