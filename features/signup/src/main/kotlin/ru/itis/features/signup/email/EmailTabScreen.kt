package ru.itis.features.signup.email

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.R
import ru.itis.core.ui.common.FieldCorrectnessCheck
import ru.itis.core.ui.components.AppTextField
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.theme.AppTheme
import ru.itis.core.ui.utils.EmailPassData
import ru.itis.features.signup.SignUpUIState


@Composable
internal fun EmailTabRoute(
    uiState: SignUpUIState,
    onEmailChange: (String) -> Unit,
    onNextClick: (EmailPassData) -> Unit
) {

    EmailTabScreen(
        uiState = uiState,
        onNextClick = onNextClick,
        onEmailChange = onEmailChange
    )

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun EmailTabScreen(
    uiState: SignUpUIState,
    onNextClick: (EmailPassData) -> Unit,
    onEmailChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.height(140.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))
        AppTextField(
            text = uiState.inputEmail.email,
            placeholder = stringResource(id = R.string.enter_email_hint),
            onChange = onEmailChange,
            isError = uiState.inputEmail.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions { keyboardController?.hide() },
            isEnabled = uiState.inputEmail.isFieldEnabled
        )

        Spacer(modifier = Modifier.height(16.dp))
        AuthButton(
            text = stringResource(id = R.string.next),
            style = AppTheme.typography.text14M,
            enabled = uiState.inputEmail.isError is FieldCorrectnessCheck.Success && uiState.networkAvailable,
            onClick = { onNextClick(EmailPassData(uiState.inputEmail.email)) }
        )
    }
}
