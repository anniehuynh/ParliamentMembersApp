package com.example.parliamentmembersapp.members_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.parliamentmembersapp.R
import com.example.parliamentmembersapp.databinding.FragmentMembersDetailBinding


/**
 * MemberDetailFragment
 * by An Huynh
 * on 4/10/2021
 */
class MembersDetailFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMembersDetailBinding>(inflater,
            R.layout.fragment_members_detail,container,false)
        return binding.root
    }

}