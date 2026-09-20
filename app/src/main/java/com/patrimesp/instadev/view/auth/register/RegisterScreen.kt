package com.patrimesp.instadev.view.auth.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import com.aristidevs.instadev.view.core.components.InstaButtonSecondary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel = viewModel()) {
    val registerUiState by registerViewModel.registerUiState.collectAsStateWithLifecycle()

    Scaffold(
         topBar = {
             TopAppBar(
                 colors = TopAppBarDefaults.topAppBarColors(
                     containerColor = MaterialTheme.colorScheme.background
                 ),
                 title = {},
                 navigationIcon = {
                     Icon(
                         imageVector = Icons.Default.ArrowBack,
                         contentDescription = "back",
                         tint = MaterialTheme.colorScheme.onSurfaceVariant
                     )
                 }
             )
         }
    ){ padding ->
        Box(Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(padding)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
            ) {
                InstaText(
                    text = if (registerUiState.isEmailRegistryTapped)
                        stringResource(R.string.register_screen_title_email) else
                        stringResource(R.string.register_screen_title_phone),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(4.dp))
                InstaText(
                    text = if (registerUiState.isEmailRegistryTapped)
                        stringResource(R.string.register_screen_subtitle_email) else
                            stringResource(R.string.register_screen_subtitle_phone),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(16.dp))
                InstaTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = registerUiState.phone,
                    label = if (registerUiState.isEmailRegistryTapped)
                        stringResource(R.string.register_screen_textfield_register_email) else
                        stringResource(R.string.register_screen_textfield_register_phone),
                    onValueChange = { registerViewModel.onPhoneChanged(it) })
                Spacer(Modifier.height(12.dp))
                InstaText(
                    text = if (registerUiState.isEmailRegistryTapped)
                        stringResource(R.string.register_screen_body_email) else
                            stringResource(R.string.register_screen_body),
                )
                Spacer(Modifier.height(12.dp))
                InstaButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.register_screen_button_next),
                    onClick = {
                        if (registerUiState.isEmailRegistryTapped)
                        registerViewModel.onEmailRegistryButton() else
                        registerViewModel.onPhoneRegistryButton()
                              },
                )
                Spacer(Modifier.height(4.dp))
                InstaButtonSecondary(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {  registerViewModel.onEmailRegistryButton() },
                    title = if (registerUiState.isEmailRegistryTapped)
                        stringResource(R.string.register_screen_button_register_with_phone) else
                        stringResource(R.string.register_screen_button_register_with_email),
                    titleColor = MaterialTheme.colorScheme.onBackground,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
                )
                Spacer(Modifier.weight(1f))
                InstaText(
                    modifier = Modifier.padding(4.dp),
                    text = stringResource(R.string.register_screen_text_find_my_account), color= MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}