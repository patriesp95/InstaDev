package com.patrimesp.instadev.view.auth.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel: ViewModel() {
    val _registerUiState = MutableStateFlow(RegisterUiState())
    var registerUiState: StateFlow<RegisterUiState> = _registerUiState

    fun onChangeMode() {
        _registerUiState.update { state ->
            state.copy(isPhoneMode = !state.isPhoneMode, value = "")
        }
    }

    fun onRegisterChanged(value: String) {
        _registerUiState.update { state ->
            val isEnabled = if (state.isPhoneMode) {
                state.value.length == 9
            } else {
                Patterns.EMAIL_ADDRESS.matcher(value).matches()
            }

            state.copy(isRegisterEnabled = isEnabled, value = value)
        }
    }
}
data class RegisterUiState(
    val value: String = "",
    val isPhoneMode: Boolean = true,
    val isRegisterEnabled: Boolean = false,
)