package com.patrimesp.instadev.view.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aristidevs.instadev.view.core.components.InstaButton
import com.aristidevs.instadev.view.core.components.InstaText
import com.aristidevs.instadev.view.core.components.InstaTextField
import com.patrimesp.instadev.R

@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel = viewModel()) {
    val registerUiState by registerViewModel.registerUiState.collectAsStateWithLifecycle()

    Scaffold { padding ->
        Box(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(padding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                InstaText(
                    text = if (registerUiState.isEmailRegistryTapped)
                        stringResource(R.string.register_screen_title_email) else
                        stringResource(R.string.register_screen_title_phone),
                    modifier = Modifier.padding(top = 22.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
                InstaText(
                    text = if (registerUiState.isEmailRegistryTapped)
                        stringResource(R.string.register_screen_subtitle_email) else
                            stringResource(R.string.register_screen_subtitle_phone),
                    modifier = Modifier.padding(top = 22.dp),
                )

                InstaTextField(
                    modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                    value = registerUiState.phone,
                    label = if (registerUiState.isEmailRegistryTapped)
                        stringResource(R.string.register_screen_textfield_register_email) else
                        stringResource(R.string.register_screen_textfield_register_phone),
                    onValueChange = { registerViewModel.onPhoneChanged(it) })

                InstaText(
                    text = if (registerUiState.isEmailRegistryTapped)
                        stringResource(R.string.register_screen_body_email) else
                            stringResource(R.string.register_screen_body),
                    modifier = Modifier.padding(top = 22.dp),
                )

                InstaButton(
                    modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                    text = stringResource(R.string.register_screen_button_next),
                    onClick = {
                        if (registerUiState.isEmailRegistryTapped)
                        registerViewModel.onEmailRegistryButton() else
                        registerViewModel.onPhoneRegistryButton()
                              },
                )

                InstaButton(
                    modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                    text = if (registerUiState.isEmailRegistryTapped)
                        stringResource(R.string.register_screen_button_register_with_phone) else
                        stringResource(R.string.register_screen_button_register_with_email),
                    onClick = {  registerViewModel.onEmailRegistryButton() },
                )

                Spacer(Modifier.weight(1f))

            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}