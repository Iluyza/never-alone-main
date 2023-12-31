package ru.itis.core.ui.theme

import androidx.compose.ui.graphics.Color


fun lightColors(
    statusBar: Color = LightBackground,
    backgroundPrimary: Color = LightBackground,
    backgroundOnSecondary: Color = LightOnSecondary,
    buttonOnPrimary: Color = LightOnBackgroundBTN,
    textHighEmphasis: Color = LightTextHighEmphasis,
    textMediumEmphasis: Color = LightTextMediumEmphasis,
    textLowEmphasis: Color = LightTextLowEmphasis,
    errorOnPrimary: Color = LightError,
    successOnPrimary: Color = LightSuccess,
    bottomBarOnPrimary: Color = LightOnBackgroundBNV,
    textFieldOnPrimary: Color = LightOnBackgroundTF,
    googleButton: Color = LightBackground,
    googleButtonText: Color = LightTextMediumEmphasis,
    disabledButton: Color = LightOnBackgroundBTNDisabled,
    checkBoxOnPrimary: Color = LightOnBackgroundChB,
): AppColors = AppColors(
    statusBar = statusBar,
    backgroundPrimary = backgroundPrimary,
    buttonOnPrimary = buttonOnPrimary,
    backgroundOnSecondary = backgroundOnSecondary,
    textHighEmphasis = textHighEmphasis,
    textMediumEmphasis = textMediumEmphasis,
    textLowEmphasis = textLowEmphasis,
    errorOnPrimary = errorOnPrimary,
    successOnPrimary = successOnPrimary,
    bottomBarOnPrimary = bottomBarOnPrimary,
    textFieldOnPrimary = textFieldOnPrimary,
    checkBoxOnPrimary = checkBoxOnPrimary,
    googleButton = googleButton,
    googleButtonText = googleButtonText,
    disabledButton = disabledButton,
    isLight = true,
)
