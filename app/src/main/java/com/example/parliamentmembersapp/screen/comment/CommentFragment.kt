package com.example.parliamentmembersapp.screen.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.parliamentmembersapp.R
import com.example.parliamentmembersapp.databinding.FragmentCommentBinding
import com.example.parliamentmembersapp.screen.member_detail.MembersDetailFragmentArgs

/**
 * Comment fragment to display comment(s) of one member
 * by An Huynh
 * on 7/10/2021
 */

class CommentFragment : Fragment() {

    private lateinit var binding: FragmentCommentBinding
    private lateinit var commentViewModel: CommentViewModel
    private val args: MembersDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_comment, container, false
        )
        // Specify the fragment view as the lifecycle owner of the binding.
        binding.lifecycleOwner = viewLifecycleOwner

        // Get argument at the destination
        val personNumber = args.personNumber

        // Initialize ViewModel
        commentViewModel = ViewModelProvider(this, CommentViewModelFactory())
            .get(CommentViewModel::class.java)

        //get member's comment
        commentViewModel.getComment(personNumber)

        //set adapter
        val adapter = CommentAdapter()
        binding.commentList.adapter = adapter

        // observe changes when the comment LiveData is updated
        commentViewModel.memberComments.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        return binding.root
    }


}


