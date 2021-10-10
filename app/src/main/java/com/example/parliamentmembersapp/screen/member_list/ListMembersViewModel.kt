package com.example.parliamentmembersapp.screen.member_list

/**
 * ListMembersViewModel
 * by An Huynh
 * created on 29/9/2021
 */
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembersapp.database.MemberOfParliament
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

    //get member's detail fragment
    private fun getMembersDetails() {
        viewModelScope.launch {
            displayMember.value = repository.getMemberFromDatabase()
        }
    }

    //Click Handler function
    fun onMemberNameClicked(id: Int) {
        _navigateToMembersDetail.value = id
    }

    //Define a method to call after the app has navigate to MemberDetailsFragment
    private val _navigateToMembersDetail = MutableLiveData<Int>()
    val navigateToMembersDetail
        get() = _navigateToMembersDetail


    fun onMembersDetailNavigated() {
        _navigateToMembersDetail.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}