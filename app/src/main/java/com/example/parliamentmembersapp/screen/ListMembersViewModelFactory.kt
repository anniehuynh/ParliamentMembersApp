package com.example.parliamentmembersapp.screen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembersapp.database.MemberOfParliamentDao

class ListMembersViewModelFactory (
    private val dataSource: MemberOfParliamentDao,
    private val application: Application) : ViewModelProvider.Factory
{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListMembersViewModel::class.java)) {
            return ListMembersViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}