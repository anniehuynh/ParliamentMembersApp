package com.example.parliamentmembersapp.members_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.parliamentmembersapp.R
import com.example.parliamentmembersapp.databinding.FragmentMembersDetailBinding


/**
 * MemberDetailFragment
 * by An Huynh
 * on 4/10/2021
 */
class MembersDetailFragment : Fragment() {

    private lateinit var memberDetailsViewModel: MembersDetailViewModel
    private val args: MembersDetailFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val personNumber = args.personNumber

        val binding = DataBindingUtil.inflate<FragmentMembersDetailBinding>(inflater,
            R.layout.fragment_members_detail,container,false)

        //Fragment view as lifeCycle owner
        binding.lifecycleOwner = viewLifecycleOwner

        //ViewModelProvider
        memberDetailsViewModel = ViewModelProvider(this).get(MembersDetailViewModel::class.java)

        //get member detail
        memberDetailsViewModel.getMembersDetail(personNumber)

        //Observe Member details
        memberDetailsViewModel.memberDetails.observe(viewLifecycleOwner, { newMember ->
            newMember?.let {
                //update new member
                binding.member = newMember

            }
        })
        //binding btn
        binding.backToListBtn.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_membersDetailFragment_to_listMembersFragment)
        }



        return binding.root
    }

}