package com.example.parliamentmembersapp.screen

/**
 *  List Member Fragment that will show list of members
 *  author: An Huynh
 *  Date created 17/9/2021
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembersapp.database.MemberDatabase
import com.example.parliamentmembersapp.databinding.FragmentListMembersBinding

class ListMembersFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListMembersBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val dataSource = MemberDatabase.getInstance().memberDatabseDao
        val viewModelFactory = ListMembersViewModelFactory(dataSource, application)
        val viewModelMember: ListMembersViewModel by lazy {
            ViewModelProvider(this, viewModelFactory).get(ListMembersViewModel::class.java)
        }

        val adapter = MemberAdapter()

        // put the new list to adapter
        binding.memberList.adapter = adapter
        binding.viewModel = viewModelMember
        viewModelMember.members.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        return binding.root
    }
}


