package com.example.parliamentmembersapp.screen.member_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.parliamentmembersapp.R
import com.example.parliamentmembersapp.databinding.FragmentMembersDetailBinding
import kotlinx.android.synthetic.main.fragment_members_detail.view.*


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

        val binding = DataBindingUtil.inflate<FragmentMembersDetailBinding>(inflater,
            R.layout.fragment_members_detail,container,false)

        val personNumber = args.personNumber

        var ratingBar: RatingBar = binding.ratingBar
        var comments = binding.commentBox


        //Fragment view as lifeCycle owner
        binding.lifecycleOwner = viewLifecycleOwner

        //ViewModelProvider
        memberDetailsViewModel = ViewModelProvider(this).get(MembersDetailViewModel::class.java)

        //get member detail
        memberDetailsViewModel.getMembersDetail(personNumber)

        //get rating
        memberDetailsViewModel.getRating(personNumber)

        //get comments
        memberDetailsViewModel.getComments(personNumber)

        //Observe Member details
        memberDetailsViewModel.memberDetails.observe(viewLifecycleOwner, { newMember ->
            newMember?.let {
                //update new member
                binding.member = newMember

                //Using Glide to display image
                Glide
                    .with(this)
                    .load("https://avoindata.eduskunta.fi/${newMember.pictureUrl}")
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.memberImage)
            }
        })
        //submit btn
        binding.submitBtn.setOnClickListener{view: View ->
            val rates = ratingBar.rating
            val comment = comments.text.toString()
            Toast.makeText(context, "Successfully Submit Rating", Toast.LENGTH_LONG).show()
            memberDetailsViewModel.addRatingAndComment(personNumber, rates, comment)
        }

        //update comments
        memberDetailsViewModel.memberComments.observe(viewLifecycleOwner, {
            memberDetailsViewModel.getComments(personNumber)
        })

        //update rating and calculate average rate
        memberDetailsViewModel.rate.observe(viewLifecycleOwner, {
            memberDetailsViewModel.averageRating(it)
        })

        //display average rating points
        memberDetailsViewModel.averageRating.observe(viewLifecycleOwner,{rate ->
            binding.averageRate.text = getString(R.string.rating, rate)
        })



        return binding.root
    }

}