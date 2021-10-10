package com.example.parliamentmembersapp.screen.comment

import androidx.lifecycle.*
import com.example.parliamentmembersapp.database.Comment
import com.example.parliamentmembersapp.repository.MemberRepository


/**
 * CommentViewModel class contains the logic for displaying comments for the member
 * by An Huynh
 * on 8/10/2021
 */
class CommentViewModel: ViewModel() {
    //get data from repository
    private val repository = MemberRepository()

    //comment
    private lateinit var _memberComments: LiveData<List<Comment>>
    val memberComments: LiveData<List<Comment>>
        get() = _memberComments

    //get comment function
    fun getComment(personNumber: Int){
        _memberComments = repository.getComment(personNumber)
    }


}