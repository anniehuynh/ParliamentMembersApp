package com.example.parliamentmembersapp.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.databinding.MemberItemLayoutBinding

class MemberAdapter : ListAdapter<MemberOfParliament, MemberAdapter.ViewHolder>(
    MemberOfParliamentDiffCallBack()
) {
    class ViewHolder private constructor(private val binding: MemberItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MemberOfParliament) {
            binding.member = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MemberItemLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

/*class MemberAdapter : ListAdapter<MemberOfParliament, MemberAdapter.ViewHolder>(MemberOfParliamentDiffCallBack()) {
    class ViewHolder private constructor(private val binding: MemberItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //val fullNameTextView = binding.memberName

        fun bind(member: MemberOfParliament) {
            binding.memberName.text = "Hello"
            //binding.member = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MemberItemLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    //@SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val member = getItem(position)
        Log.i("member adapter", member.toString())
        holder.bind(member)
        /*val textView = holder.fullNameTextView
        textView.text = "${member.first} ${member.last}"*/
    }

}*/

class MemberOfParliamentDiffCallBack : DiffUtil.ItemCallback<MemberOfParliament>() {
    override fun areItemsTheSame(
        oldItem: MemberOfParliament,
        newItem: MemberOfParliament
    ): Boolean {
        return oldItem.personNumber == newItem.personNumber
    }

    override fun areContentsTheSame(
        oldItem: MemberOfParliament,
        newItem: MemberOfParliament
    ): Boolean {
        return oldItem == newItem
    }
}