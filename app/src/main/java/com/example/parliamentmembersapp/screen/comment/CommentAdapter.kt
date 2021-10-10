package com.example.parliamentmembersapp.screen.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembersapp.database.Comment
import com.example.parliamentmembersapp.databinding.CommentItemBinding

/**
 * CommentAdapter for Comment Recyclerview
 * by An Huynh
 * on 8/10/2021
 */
class CommentAdapter : ListAdapter<Comment, CommentAdapter.ViewHolder>(
CommentDiffCallBack())
 {
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder.from(parent)
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {

         holder.bind(getItem(position))
     }

    class ViewHolder private constructor(private val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Update ViewModel to use data binding to bind the data
        fun bind(comment: Comment) {
            binding.commentData = comment
            binding.commentText.text = comment.comment
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CommentItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


}
class CommentDiffCallBack : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(
        oldItem: Comment,
        newItem: Comment
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}

