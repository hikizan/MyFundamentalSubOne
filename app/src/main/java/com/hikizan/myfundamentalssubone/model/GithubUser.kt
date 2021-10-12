package com.hikizan.myfundamentalssubone.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    var name: String,
    var username: String,
    var photo: Int,
    var location: String,
    var repository: Int,
    var company: String,
    var followers: Int,
    var following: Int,
) : Parcelable
