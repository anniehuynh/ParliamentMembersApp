package com.example.parliamentmembersapp.screen

/**
 * ListMembersViewModel
 * by An Huynh
 * created on 29/9/2021
 */
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.database.MemberOfParliamentDao
import com.example.parliamentmembersapp.formatMembers
import kotlinx.coroutines.launch

class ListMembersViewModel(dataSource: MemberOfParliamentDao,
                           application: Application
) : ViewModel() {

    val database = dataSource

    private var displayMember = MutableLiveData<MemberOfParliament>()

    var members = database.getAllMembers()
/*
    val nightsString = Transformations.map(members) { members ->
        formatMembers(members, application.resources)
    }

 */

    init {
        getMembersDetails()
    }

    private fun getMembersDetails() {
        viewModelScope.launch {
            displayMember.value = getMemberFromDatabase()
        }
    }

    private suspend fun getMemberFromDatabase(): MemberOfParliament? = database.getOneMember()
}