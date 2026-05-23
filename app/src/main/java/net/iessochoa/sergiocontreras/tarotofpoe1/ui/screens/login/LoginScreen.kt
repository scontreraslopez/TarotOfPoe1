package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.components.LoginButton
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.components.Password
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.components.RegisterButton
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.components.SocialLogin
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.components.UserName
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.Spacing
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.TarotOfPoe1Theme

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login
 * Created by: Contr
 * On: 23/05/2026 at 21:59
 * Creado en Settings -> Editor -> File and Code Templates
 */



@Composable
fun Body(
    uiState: LoginScreenUiState,
    onUserNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onUserRegister: () -> Unit,
    onUserLogin: () -> Unit
) {


    val userName = uiState.username
    val password = uiState.password
    val isLoginEnable = uiState.isLoginEnabled
    val isLoginError = uiState.isLoginError

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        UserName(
            userName = userName,
            isLoginError = isLoginError,
            updateUserName = onUserNameChange
        )
        Password(
            password = password,
            isLoginError = isLoginError,
            updatePassword = onPasswordChange
        )
        Row() {
            RegisterButton(onRegisterButtonClick = onUserRegister)
            Spacer(modifier = Modifier.width(Spacing.lg))
            LoginButton(isLoginEnable, onUserLogin)
        }
        HorizontalDivider(modifier = Modifier.padding(Spacing.md),
            thickness = 1.dp)
        SocialLogin()
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    TarotOfPoe1Theme() {
        Body(
            uiState = LoginScreenUiState(),
            onUserRegister = { },
            onUserLogin = { },
            onUserNameChange = { },
            onPasswordChange = { }
        )
    }
}