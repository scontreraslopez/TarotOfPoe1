package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login
 * Created by: Contr
 * On: 23/05/2026 at 23:27
 * Creado en Settings -> Editor -> File and Code Templates
 */


data class LoginScreenUiState(
    val username: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false,
    val isLoginError: Boolean = false,
    val isLoading: Boolean = false
)