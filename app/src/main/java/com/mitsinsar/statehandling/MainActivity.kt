package com.mitsinsar.statehandling

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mitsinsar.statehandling.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    private val userUiStateCollector: suspend (UserUiState) -> Unit = { uiState ->
        updateUiState(uiState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        initObservers()
    }

    private fun initUi() {
        binding.getUserButton.setOnClickListener { mainViewModel.getUser() }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                mainViewModel.userStateFlow.collectLatest(userUiStateCollector)
            }
        }
    }

    private fun updateUiState(uiState: UserUiState) {
        with(binding) {
            emptyStateGroup.isVisible = uiState.isUserGroupVisible
            uiState.user?.run {
                nameTextView.text = name
                statusCheckBox.isChecked = isActive
            }
        }
    }
}
