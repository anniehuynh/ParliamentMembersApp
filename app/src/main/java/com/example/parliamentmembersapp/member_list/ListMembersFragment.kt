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
        val dataSource = MemberDatabase.getInstance().memberDatabseDao
        val viewModelFactory = ListMembersViewModelFactory(dataSource, application)
        val viewModelMember: ListMembersViewModel by lazy {
            ViewModelProvider(this, viewModelFactory).get(ListMembersViewModel::class.java)
        }
/**
 * Allows Binding to Observe this fragment
 */
        val adapter = MemberAdapter(MemberListener {
        personNumber -> Toast.makeText(context, "${personNumber}", Toast.LENGTH_LONG).show()
})



        // put the new list to adapter
        binding.memberList.adapter = adapter
        binding.viewModel = viewModelMember
        viewModelMember.members.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this
        return binding.root
    }
}


