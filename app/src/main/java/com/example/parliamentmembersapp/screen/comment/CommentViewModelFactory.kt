package com.example.parliamentmembersapp.screen.comment


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * ViewModelFatory for CommentViewModel
 * by An Huynh
 * on 8/10/2021
 */
class CommentViewModelFactory : ViewModelProvider.Factory
{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            return CommentViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
