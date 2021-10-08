package com.example.parliamentmembersapp.screen.member_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembersapp.database.*
import com.example.parliamentmembersapp.repository.MemberRepository
import kotlinx.coroutines.launch
import java.util.*
import java.util.Calendar

enum class MembersApiStatus { LOADING, ERROR, DONE }

/**
 * ViewModel for MembersDetailFragment
 * by An Huynh
 * on 4/10/2021.
 */
class MembersDetailViewModel() : ViewModel() {
    private val repository = MemberRepository()
    private val database: MemberOfParliamentDao = MemberDatabase.getInstance().memberDatabaseDao

    private val _averageRating = MutableLiveData<Double>()
    val averageRating: LiveData<Double>
        get() = _averageRating


    //comment
    private lateinit var _memberComments: LiveData<List<Comment>>
    val memberComments: LiveData<List<Comment>>
        get() = _memberComments
    //rating
    private lateinit var _rate: LiveData<List<Double>>
    val rate: LiveData<List<Double>>
        get() = _rate

    //member details
    private lateinit var _memberDetails: LiveData<MemberOfParliament>
    val memberDetails: LiveData<MemberOfParliament>
        get() = _memberDetails

    //personNumber
    private lateinit var _personNumber: LiveData<Int>
    val personNumber: LiveData<Int>
        get() = _personNumber


    //get the member
    fun getMembersDetail(personNumber: Int) {
        _memberDetails = database.getMemberDetails(personNumber)
    }

    //use coroutine to update rating
    fun addRatingAndComment(personNumber: Int, rating: Float, comment: String) {
        viewModelScope.launch {
            database.insertRating(Rating(personNumber = personNumber, rating = rating))

            if(comment.isNotEmpty()){
                database.insertComment(Comment(personNumber = personNumber, comment = comment))
            }
        }
    }

    //insert comments
    fun getComments(personNumber: Int){
        _memberComments = database.getComment(personNumber)
    }
    //get rating
    fun getRating(personNumber: Int) {
        _rate = database.getMembersRating(personNumber)
    }

    //Calculate rating average
    fun averageRating(ratings: List<Double>) {
        if (ratings.isNotEmpty()) {
            _averageRating.value = ratings.average()
        } else _averageRating.value = 0.0
    }

}
