package com.mitsinsar.statehandling

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val isActive: Boolean
) : Parcelable
