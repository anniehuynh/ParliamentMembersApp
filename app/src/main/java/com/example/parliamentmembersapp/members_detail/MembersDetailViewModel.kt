package com.example.parliamentmembersapp.members_detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.database.MemberOfParliamentDao
import kotlinx.coroutines.launch

/**
 * ViewModel for MembersDetailFragment
 * by An Huynh
 * on 4/10/2021.
 */
class MembersDetailViewModel(private val personNumber: MemberOfParliamentDao,
                             datasource: Application
) : ViewModel() {
    /**
     * Hold a reference to SleepDatabase via its SleepDatabaseDao.
     */
    val database = datasource

    private lateinit var member: MutableLiveData<MemberOfParliament>


    init {
        getMembersDetails()
    }

    private fun getMembersDetails() {
        viewModelScope.launch {
           // member.value = getMemberFromDatabase()
        }
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
     fun doneNavigating(){
         _navigateToListMembers.value = null
     }

    fun onClose(){
        _navigateToListMembers.value = true
    }
}
