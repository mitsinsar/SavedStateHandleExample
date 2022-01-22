package com.mitsinsar.statehandling

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUiState(
    val user: User?
) : Parcelable {
    val isUserGroupVisible: Boolean
        get() = user != null

    companion object {
        fun create(user: User? = null): UserUiState {
            return UserUiState(user)
        }
    }
}
