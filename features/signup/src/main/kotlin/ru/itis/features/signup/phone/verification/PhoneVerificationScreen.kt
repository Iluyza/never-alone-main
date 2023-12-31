@file:OptIn(ExperimentalComposeUiApi::class)

package ru.itis.features.signup.phone.verification

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.LoginTextField
import ru.itis.core.ui.theme.AppTheme


@Composable
fun PhoneVerificationRoute(
    deps: PhoneVerificationDeps,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onTextSignInClick: () -> Unit
) {

    val phoneVerificationComponentViewModel = viewModel<PhoneVerificationComponentViewModel>(
        factory = PhoneVerificationComponentViewModelFactory(deps)
    )

    val phoneVerificationViewModel = viewModel<PhoneVerificationViewModel>(
        factory = phoneVerificationComponentViewModel.phoneVerificationDepsComponent.getViewModelFactory()
    )

    val uiState by phoneVerificationViewModel.phoneVerificationUIState.collectAsState()

    val context = LocalContext.current

    PhoneVerificationScreen(
        uiState = uiState,
        onCodeChanged = phoneVerificationViewModel::onCodeChange,
        onNextClick = onNextClick,
        onBackClick = onBackClick,
        onTextSignInClick = onTextSignInClick,
        onRequestMoreClick = { phoneVerificationViewModel.onRequestClick(context as ComponentActivity) }
    )

}

@Composable
private fun PhoneVerificationScreen(
    uiState: PhoneVerificationScreenUIState,
    onCodeChanged: (String) -> Unit,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onTextSignInClick: () -> Unit,
    onRequestMoreClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
    ) {
        IconButton(
            onClick = {
                keyboardController?.hide()
                onBackClick()
            },
            content = {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = AppTheme.colors.textHighEmphasis
                )
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.enter_verification_code),
                style = AppTheme.typography.text14M,
                color = AppTheme.colors.textHighEmphasis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.enter_six_digit_code),
                style = AppTheme.typography.text14R,
                color = AppTheme.colors.textMediumEmphasis
            )
            Text(
                modifier = Modifier.clickable(
                    role = Role.Button,
                    onClick = onRequestMoreClick
                ),
                text = stringResource(R.string.request_more),
                style = AppTheme.typography.text14M,
                color = AppTheme.colors.textHighEmphasis,
            )
            Spacer(modifier = Modifier.height(16.dp))
            LoginTextField(
                inputValue = uiState.inputCode.code,
                placeholder = stringResource(id = R.string.verification_code),
                onValueChange = onCodeChanged
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthButton(
                text = stringResource(id = R.string.next),
                style = AppTheme.typography.text14M,
                onClick = onNextClick
            )

        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Row {
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = stringResource(id = R.string.have_an_account),
                    color = AppTheme.colors.textMediumEmphasis,
                    style = AppTheme.typography.text14M
                )
                Text(
                    modifier = Modifier.clickable(
                        role = Role.Button,
                        onClick = onTextSignInClick
                    ),
                    text = stringResource(id = R.string.signin),
                    color = AppTheme.colors.textHighEmphasis,
                    style = AppTheme.typography.text14M
                )
            }
        }
    }

}

@Preview(locale = "en")
@Preview(uiMode = UI_MODE_NIGHT_YES, locale = "tt")
@Composable
fun PhoneVerificationScreenPreview() {
    PhoneVerificationScreen(
        uiState = PhoneVerificationScreenUIState(),
        onCodeChanged = {},
        onNextClick = {},
        onBackClick = {},
        onRequestMoreClick = {},
        onTextSignInClick = {}
    )
}
