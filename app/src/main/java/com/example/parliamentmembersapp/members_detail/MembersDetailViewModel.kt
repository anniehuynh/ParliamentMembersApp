package com.example.parliamentmembersapp.members_detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembersapp.database.MemberDatabase
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.database.MemberOfParliamentDao
import kotlinx.coroutines.launch

enum class MembersApiStatus { LOADING, ERROR, DONE }

/**
 * ViewModel for MembersDetailFragment
 * by An Huynh
 * on 4/10/2021.
 */
class MembersDetailViewModel() : ViewModel() {

    private val database: MemberOfParliamentDao


    private lateinit var _memberDetails: LiveData<MemberOfParliament>
    val memberDetails: LiveData<MemberOfParliament>
        get() = _memberDetails

    private lateinit var _personNumber: LiveData<Int>
    val personNumber: LiveData<Int>
    get() = _personNumber

    init {
        database = MemberDatabase.getInstance().memberDatabaseDao
    }


    //get the member
    fun getMembersDetail(personNumber:Int) {
        _memberDetails = database.getMemberDetails(personNumber)
    }


    /**
     * Variable that tells the fragment whether it should navigate to ListMembersFragment.
     * Making it private so as not to expose the ability to set MutableLiveData to the fragment
     */
    private val _navigateToListMembers = MutableLiveData<Boolean?>()


    //When true immediately navigate back to the ListMembersFragment

    val navigateToListMembersFragment: LiveData<Boolean?>
        get() = _navigateToListMembers


    //When the navigation is done
    fun doneNavigating() {
        _navigateToListMembers.value = null
    }

    fun onClose() {
        _navigateToListMembers.value = true
    }
}
