@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalPagerApi::class)

package ru.itis.features.signup

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import ru.itis.core.ui.R
import ru.itis.core.ui.components.NoInternetWarn
import ru.itis.core.ui.theme.AppTheme
import ru.itis.core.ui.utils.EmailPassData
import ru.itis.features.signup.email.EmailTabRoute
import ru.itis.features.signup.phone.PhoneRoute
import ru.itis.features.signup.utils.Constants.PAGE_COUNT
import ru.itis.features.signup.utils.Constants.PAGE_EMAIL
import ru.itis.features.signup.utils.Constants.TAB_PHONE


@Composable
fun SignUpRoute(
    deps: SignUpDeps,
    onNextWithEmailClick: (EmailPassData) -> Unit,
    onNextWithPhoneClick: () -> Unit,
    onBackClick: () -> Unit,
    onTextSignInClick: () -> Unit
) {

    val signUpComponentViewModel = viewModel<SignUpComponentViewModel>(
        factory = SignUpComponentViewModelFactory(deps)
    )

    val signUpViewModel = viewModel<SignUpViewModel>(
        factory = signUpComponentViewModel.signUpComponent.getViewModelFactory()
    )

    val uiState by signUpViewModel.signUpUIState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(uiState.signUpProcess.signUpSuccess) {
        if (uiState.signUpProcess.signUpSuccess) {
            delay(300)
            onBackClick()
        }
    }


    LaunchedEffect(key1 = uiState.snackBar.show) {
        delay(1500L)
        if (uiState.snackBar.show) {
            signUpViewModel.hideSnackbar()
        }
    }

    LaunchedEffect(key1 = uiState.couldNavigate) {
        if (uiState.couldNavigate) {
            Log.e("DEBUG", uiState.couldNavigate.toString())
            onNextWithEmailClick(EmailPassData(uiState.inputEmail.email))
            signUpViewModel.setCouldNotNavigate()
        }
    }

    SignUpScreen(
        uiState = uiState,
        onTextSignInClick = onTextSignInClick,
        onBackClick = onBackClick,
        onTabSelected = signUpViewModel::onTabSelected,
        onPhoneRoute = {
            PhoneRoute(
                uiState = uiState,
                onNextClick = {
                    signUpViewModel.onSendCodeClick(context as ComponentActivity).also {
                        onNextWithPhoneClick()
                    }
                },
                onPhoneChange = signUpViewModel::onPhoneChange
            )
        },
        onEmailRoute = {
            EmailTabRoute(
                uiState = uiState,
                onNextClick = {
                    Log.e("DEBUG", "email: ${it.email}")
                    signUpViewModel.checkEmail(it.email)
                },
                onEmailChange = signUpViewModel::onEmailChange
            )
        }
    )
}

@Composable
private fun SignUpScreen(
    uiState: SignUpUIState,
    onTextSignInClick: () -> Unit,
    onBackClick: () -> Unit,
    onTabSelected: (Int) -> Unit,
    onPhoneRoute: @Composable () -> Unit,
    onEmailRoute: @Composable () -> Unit
) {
    val focusManager = LocalFocusManager.current

    val pagerState = rememberPagerState(initialPage = uiState.activeTab)

    LaunchedEffect(key1 = uiState.activeTab) {
        pagerState.animateScrollToPage(uiState.activeTab)
    }

    BackHandler(enabled = uiState.activeTab != TAB_PHONE) {
        focusManager.clearFocus()
        onTabSelected(TAB_PHONE)
    }

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
    ) {
        Column {
            NoInternetWarn(internetAvailable = uiState.networkAvailable)
            IconButton(
                onClick = {
                    focusManager.clearFocus()
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
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.app_name),
                style = AppTheme.typography.text36R,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.textHighEmphasis
            )
            Spacer(modifier = Modifier.height(24.dp))
            Tabs(
                modifier = Modifier.fillMaxWidth(),
                pagerState = pagerState,
                onTabClicked = onTabSelected,
                focusManager = focusManager
            )
            SignUpMethodPager(
                pagerState = pagerState,
                onPhoneRoute = onPhoneRoute,
                onEmailRoute = onEmailRoute
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
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            ru.itis.core.ui.components.Snackbar(
                message = uiState.snackBar.message,
                isError = uiState.snackBar.isError,
                visible = uiState.snackBar.show
            )
        }
    }
}

@Composable
private fun Tabs(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    focusManager: FocusManager,
    onTabClicked: (Int) -> Unit
) {
    TabRow(
        modifier = modifier,
        backgroundColor = AppTheme.colors.backgroundPrimary,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        repeat(PAGE_COUNT) { index ->
            when (index) {
                TAB_PHONE -> {
                    Tab(
                        text = {
                            Text(
                                text = stringResource(R.string.phone),
                                style = AppTheme.typography.text14R,
                                color = if (pagerState.currentPage == index) AppTheme.colors.textHighEmphasis else AppTheme.colors.textLowEmphasis
                            )
                        },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            focusManager.clearFocus()
                            onTabClicked(TAB_PHONE)
                        },
                    )
                }
                PAGE_EMAIL -> {
                    Tab(
                        text = {
                            Text(
                                text = stringResource(R.string.email),
                                style = AppTheme.typography.text14R,
                                color = if (pagerState.currentPage == index) AppTheme.colors.textHighEmphasis else AppTheme.colors.textLowEmphasis
                            )
                        },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            focusManager.clearFocus()
                            onTabClicked(PAGE_EMAIL)
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun SignUpMethodPager(
    pagerState: PagerState,
    onPhoneRoute: @Composable () -> Unit,
    onEmailRoute: @Composable () -> Unit
) {
    HorizontalPager(
        modifier = Modifier,
        count = PAGE_COUNT,
        state = pagerState,
        itemSpacing = 8.dp
    ) { page ->
        when (page) {
            TAB_PHONE -> {
                onPhoneRoute()
            }
            PAGE_EMAIL -> {
                onEmailRoute()
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignUpPreview() {
    SignUpScreen(
        uiState = SignUpUIState(),
        onTextSignInClick = {},
        onBackClick = {},
        onTabSelected = {},
        onEmailRoute = {},
        onPhoneRoute = {}
    )
}
