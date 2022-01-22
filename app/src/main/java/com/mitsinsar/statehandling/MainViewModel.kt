package com.mitsinsar.statehandling

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    val userStateFlow: StateFlow<UserUiState>
        get() = _userStateFlow
    private val _userStateFlow = MutableStateFlow(UserUiState.create())

    fun getUser() {
        _userStateFlow.value = updateUserUiState()
    }

    private fun updateUserUiState(): UserUiState {
        return userStateFlow.value.copy(
            user = DummyUserProvider.createDummyUser()
        )
    }
}
