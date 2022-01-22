package com.mitsinsar.statehandling

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val userStateFlow: StateFlow<UserUiState>
        get() = _userStateFlow
    private val _userStateFlow = MutableStateFlow(
        savedStateHandle.get<UserUiState>(USER_UI_STATE_KEY) ?: UserUiState.create()
    )

    fun getUser() {
        val userUiState = updateUserUiState()
        _userStateFlow.value = userUiState
        savedStateHandle.set(USER_UI_STATE_KEY, userUiState)
    }

    private fun updateUserUiState(): UserUiState {
        return userStateFlow.value.copy(
            user = DummyUserProvider.createDummyUser()
        )
    }

    companion object {
        const val USER_UI_STATE_KEY = "user_ui_state"
    }
}
