package com.patrimesp.instadev.view.auth.register

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.aristidevs.instadev.view.core.components.InstaButtonSecondary
import dagger.hilt.android.lifecycle.HiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val uiState by registerViewModel.registerUiState.collectAsStateWithLifecycle()

    val title: String
    val subtitle: String
    val label: String
    val changeModeTitle: String

    when(uiState.isPhoneMode){
        true -> {
            title = stringResource(R.string.register_screen_title_phone)
            subtitle = stringResource(R.string.register_screen_subtitle_phone)
            label = stringResource(R.string.register_screen_textfield_register_phone)
            changeModeTitle = stringResource(R.string.register_screen_button_register_with_email)
        }
        false -> {
            title = stringResource(R.string.register_screen_title_email)
            subtitle = stringResource(R.string.register_screen_subtitle_email)
            label = stringResource(R.string.register_screen_textfield_register_email)
            changeModeTitle = stringResource(R.string.register_screen_button_register_with_phone)
        }
    }

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
                         tint = MaterialTheme.colorScheme.onSurfaceVariant,
                         modifier = Modifier.clickable{navigateBack()}
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
                AnimatedContent(title) { animatedTitle ->
                    InstaText(
                        text = animatedTitle,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(Modifier.height(4.dp))
                InstaText(
                    text = subtitle,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(16.dp))
                InstaTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.value,
                    label = label,
                    onValueChange = { registerViewModel.onRegisterChanged(it) })
                Spacer(Modifier.height(12.dp))
                InstaText(
                    text = stringResource(R.string.register_screen_body),
                )
                Spacer(Modifier.height(12.dp))
                InstaButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.register_screen_button_next),
                    enabled = uiState.isRegisterEnabled,
                    onClick = { },
                )
                Spacer(Modifier.height(4.dp))
                InstaButtonSecondary(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {  registerViewModel.onChangeMode() },
                    title = changeModeTitle,
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
    RegisterScreen {

    }
}