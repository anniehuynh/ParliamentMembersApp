package com.example.parliamentmembersapp.screen.member_list

/**
 * ListMembersViewModel
 * by An Huynh
 * created on 29/9/2021
 */
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembersapp.database.MemberDatabase
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.database.MemberOfParliamentDao
import com.example.parliamentmembersapp.network.ParliamentApi
import com.example.parliamentmembersapp.network.ParliamentApiService
import com.example.parliamentmembersapp.repository.MemberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListMembersViewModel: ViewModel() {
    private val repository = MemberRepository()


    //viewModelJob that allows cancelling the coroutines started by this ViewModel
    private val viewModelJob = Job()

    private var displayMember = MutableLiveData<MemberOfParliament>()

    var members = repository.getAllMember()

    init {
        getMembersDetails()
    }

    private fun getMembersDetails() {
        viewModelScope.launch {
            displayMember.value = repository.getMemberFromDatabase()
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    //Click Handler function
    fun onMemberNameClicked(id: Int) {
        _navigateToMembersDetail.value = id
    }

    private val _navigateToMembersDetail = MutableLiveData<Int>()
    val navigateToMembersDetail
        get() = _navigateToMembersDetail

    //Define a method to call after the app has finished navigating
    fun onMembersDetailNavigated() {
        _navigateToMembersDetail.value = null
    }
}