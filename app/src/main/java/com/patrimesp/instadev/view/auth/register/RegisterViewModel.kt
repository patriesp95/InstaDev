package com.patrimesp.instadev.view.auth.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel: ViewModel() {
    val _registerUiState = MutableStateFlow(RegisterUiState())
    var registerUiState: StateFlow<RegisterUiState> = _registerUiState

    fun onPhoneChanged(phone: String) {
        _registerUiState.update { state ->
            state.copy(phone = phone)
        }
        verifyRegistry()
    }

    fun onEmailRegistryButton() {
        _registerUiState.update { state ->
            state.copy(isEmailRegistryTapped = true)
        }
        verifyRegistry()
    }

    fun onPhoneRegistryButton() {
        _registerUiState.update { state ->
            state.copy(isEmailRegistryTapped = false)
        }
        verifyRegistry()
    }


    private fun verifyRegistry() {
        val enabledRegistry =
            isPhoneValid(_registerUiState.value.phone) || isEmailValid(_registerUiState.value.email)
        _registerUiState.update {
            it.copy(isRegisterEnabled = enabledRegistry)
        }
    }

    private fun isEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPhoneValid(phone: String): Boolean = phone.length == 9
}

data class RegisterUiState(
    val phone: String = "",
    val email: String = "",
    val isEmailRegistryTapped: Boolean = false,
    val isRegisterEnabled: Boolean = false,
)