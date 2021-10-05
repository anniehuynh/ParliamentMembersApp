package com.example.parliamentmembersapp.member_list

/**
 * ListMembersViewModel
 * by An Huynh
 * created on 29/9/2021
 */
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.database.MemberOfParliamentDao
import com.example.parliamentmembersapp.formatMembers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListMembersViewModel(dataSource: MemberOfParliamentDao,
                           application: Application
) : ViewModel() {

    val database = dataSource

    //viewModelJob that allows cancelling the coroutines started by this ViewModel
    private val viewModelJob = Job()

    private var displayMember = MutableLiveData<MemberOfParliament>()

    var members = database.getAllMembers()

    init {
        getMembersDetails()
    }

    private fun getMembersDetails() {
        viewModelScope.launch {
            displayMember.value = getMemberFromDatabase()
        }
    }

    private suspend fun getMemberFromDatabase(): MemberOfParliament? = database.getOneMember()


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _navigateToMembersDetails = MutableLiveData<Long>()
    val navigateToMembersDetails
        get() = _navigateToMembersDetails
}