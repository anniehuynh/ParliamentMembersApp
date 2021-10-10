package com.example.parliamentmembersapp.screen.member_list

/**
 * ViewModelFactory for ViewModel
 * by An Huynh
 * on 03/10/2021
 */
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ListMembersViewModelFactory : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListMembersViewModel::class.java)) {
            return ListMembersViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}