package com.example.parliamentmembersapp.screen.member_list

/**
 *  List Member Fragment that will show list of members
 *  author: An Huynh
 *  Date created 17/9/2021
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parliamentmembersapp.R
import com.example.parliamentmembersapp.databinding.FragmentListMembersBinding
import kotlinx.android.synthetic.main.member_item.view.*


class ListMembersFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     * This function uses DataBindingUtil to inflate R.layout.fragment_list_members.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListMembersBinding.inflate(inflater)

        val listMembersViewModel = ViewModelProvider(this, ListMembersViewModelFactory())
            .get(ListMembersViewModel::class.java)

        //Bind ViewModel with viewModel data in xml
        binding.viewModel = listMembersViewModel

        /**
         * Allows Binding to Observe this fragment
         */
        val adapter = MemberAdapter(MemberListener { personNumber ->
            Toast.makeText(context, "Member id: $personNumber", Toast.LENGTH_SHORT).show()
            listMembersViewModel.onMemberNameClicked(personNumber)
        })

        // put the new list to adapter
        binding.memberList.adapter = adapter

        //Observe changes of the member LiveData
        listMembersViewModel.members.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        //Observe the navigateToMembersDetail LiveData
        listMembersViewModel.navigateToMembersDetail.observe(viewLifecycleOwner, { personNumber ->
            personNumber?.let {
                this.findNavController().navigate(
                    ListMembersFragmentDirections.actionListMembersFragmentToMembersDetailFragment(
                        personNumber
                    )
                )
                listMembersViewModel.onMembersDetailNavigated()
            }
        })

        binding.lifecycleOwner = this
        return binding.root
    }
}


