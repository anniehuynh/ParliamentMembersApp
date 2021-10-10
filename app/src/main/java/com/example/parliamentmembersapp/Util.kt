package com.example.parliamentmembersapp

/**
 * Util kotlin file to display as in xml
 * by An Huynh
 * on 20.9.2021
 */
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import com.example.parliamentmembersapp.database.MemberOfParliament
import java.util.*

fun setFullName(item: MemberOfParliament) : String {
    return "${item.first} ${item.last}"
}

fun setAge(item: MemberOfParliament): String {
    return "Age: ${(Calendar.getInstance().get(Calendar.YEAR) - item.bornYear).toString()}"
}

fun formatMembers(members: List<MemberOfParliament>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        members.forEach {
            append("<br>")
            append(resources.getString(R.string.full_name))
            append("\t${setFullName(it)}<br>")
        }
    }
    // fromHtml is deprecated for target API without a flag, but since our minSDK is 19, we
    // can't use the newer version, which requires minSDK of 24
    //https://developer.android.com/reference/android/text/Html#fromHtml(java.lang.String,%20int)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(sb.toString())
    }
}