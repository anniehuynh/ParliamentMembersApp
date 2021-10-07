package com.example.parliamentmembersapp.member_list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.setAge
import com.example.parliamentmembersapp.setFullName

/**
 * Using BindingUtils as an adapter for replication data
 * into something Data Binding can display
 * by An Huynh
 * on 4/10/2021
 */
@BindingAdapter("fullName")
fun TextView.setFullNameFormatted(item: MemberOfParliament) {
    item?.let {
        text = setFullName(item)
    }
}
