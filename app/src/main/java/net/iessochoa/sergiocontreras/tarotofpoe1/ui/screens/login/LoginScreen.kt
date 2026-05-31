package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iessochoa.sergiocontreras.tarotofpoe1.R
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.components.LoginButton
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.components.Password
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.components.RegisterButton
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.components.SocialLogin
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.components.UserName
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.Cinzel
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.Spacing
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.TarotOfPoe1Theme
import kotlin.system.exitProcess

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login
 * Created by: Contr
 * On: 23/05/2026 at 21:59
 * Creado en Settings -> Editor -> File and Code Templates
 */


@Composable
fun LoginScreen(
    uiState: LoginScreenUiState,
    onLoginSuccess: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            contentAlignment = Alignment.TopEnd) {
            Header()
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Logo(Modifier)
                Spacer(modifier = Modifier.height(Spacing.md))
                Title()
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            contentAlignment = Alignment.Center) {
            Body(
                uiState = uiState,
                onUserNameChange = {},
                onPasswordChange = { },
                onUserRegister = { } ,
                onUserLogin = {}
            )
            Footer(Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun Header() {
    IconButton(modifier = Modifier.padding(top=30.dp),
        onClick = { exitProcess(0) }) {
        Icon(
            painter = painterResource(R.drawable.ic_close),
            contentDescription = "Close application"
        )
    }
}

@Composable
private fun Body(
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.xl)
        ) {
            RegisterButton(
                onRegisterButtonClick = onUserRegister,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(Spacing.md))
            LoginButton(
                isLoginEnable = isLoginEnable,
                doLogin = onUserLogin,
                modifier = Modifier.weight(1f)
            )
        }
        HorizontalDivider(modifier = Modifier.padding(Spacing.md),
            thickness = 1.dp)
        SocialLogin()
    }
}



@Composable
private fun Title() {
    Text(
        text = "Tarot Of Poe I",
        style = MaterialTheme.typography.displaySmall,
        fontFamily = Cinzel,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 3.sp
    )
}

@Composable
private fun Logo(modifier: Modifier) {
    Image(
        modifier = modifier.size(140.dp),
        painter = painterResource(R.drawable.logo_tarotofpoe1_lite),
        contentDescription = "Application logo")
}


@Composable
private fun Footer(modifier: Modifier) {
    Text(modifier = modifier
        .padding(bottom = 16.dp),
        text = "Created by Sergio Contreras (v:1.0.0)")
}



@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    TarotOfPoe1Theme() {
        LoginScreen(
            uiState = LoginScreenUiState(),
        )
    }
}