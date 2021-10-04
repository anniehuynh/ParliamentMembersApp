package com.example.parliamentmembersapp.screen

/**
 * MemberAdapter class
 * by An Huynh
 * on 3/10/2021
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.databinding.MemberItemBinding

class MemberAdapter : ListAdapter<MemberOfParliament, MemberAdapter.ViewHolder>(
    MemberOfParliamentDiffCallBack()
) {
    class ViewHolder private constructor(val binding: MemberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Update ViewModel to use data binding to bind the data
        fun bind(item: MemberOfParliament) {
            binding.member = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MemberItemBinding.inflate(layoutInflater, parent, false)
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