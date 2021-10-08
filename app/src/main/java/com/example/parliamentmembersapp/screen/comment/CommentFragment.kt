package com.example.parliamentmembersapp.screen.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parliamentmembersapp.R

/**
 * Comment fragment to display comment(s) of one member
 * by An Huynh
 * on 7/10/2021
 */

class CommentFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false)
    }


}