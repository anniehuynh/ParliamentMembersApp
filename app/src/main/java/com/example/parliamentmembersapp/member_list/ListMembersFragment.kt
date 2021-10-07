package com.example.parliamentmembersapp.member_list

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
import com.example.parliamentmembersapp.database.MemberDatabase
import com.example.parliamentmembersapp.databinding.FragmentListMembersBinding

class ListMembersFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     * This function uses DataBindingUtil to inflate R.layout.fragment_list_members.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListMembersBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val dataSource = MemberDatabase.getInstance().memberDatabaseDao
        val viewModelFactory = ListMembersViewModelFactory(dataSource, application)
        val listMembersViewModel: ListMembersViewModel by lazy {
            ViewModelProvider(this, viewModelFactory).get(ListMembersViewModel::class.java)
        }

        /**
         * Allows Binding to Observe this fragment
         */
        val adapter = MemberAdapter(MemberListener { personNumber ->
            Toast.makeText(context, "$personNumber", Toast.LENGTH_LONG).show()
            listMembersViewModel.onMemberNameClicked(personNumber)
        })
        // put the new list to adapter
        binding.memberList.adapter = adapter
        binding.viewModel = listMembersViewModel
        listMembersViewModel.members.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        //Observe the navigateToMembersDetail LiveData
        listMembersViewModel.navigateToMembersDetail.observe(viewLifecycleOwner, { personNumber ->
            personNumber?.let {
                this.findNavController().navigate(
                    ListMembersFragmentDirections
                        .actionListMembersFragmentToMembersDetailFragment(personNumber)
                )
                listMembersViewModel.onMembersDetailNavigated()
            }
        })
        //val args = MembersDetailFragmentArgs.fromBundle(requireArguments())
        //Toast.makeText(context, "Member: ${args.personNumber}", Toast.LENGTH_LONG).show()
        binding.lifecycleOwner = this
        return binding.root
    }
}


