package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login
 * Created by: Contr
 * On: 23/05/2026 at 21:59
 * Creado en Settings -> Editor -> File and Code Templates
 */

class LoginViewModel(): ViewModel() {

    private val _uiState = MutableStateFlow(LoginScreenUiState())
    val uiState: StateFlow<LoginScreenUiState> = _uiState.asStateFlow()

    fun onUsernameChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                username = value,
                isLoginEnabled = value.isNotBlank() && currentState.password.isNotBlank()
            )
        }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = value,
                isLoginEnabled = currentState.username.isNotBlank() && value.isNotBlank()
            )
        }
    }

    fun login(onSuccess: (String) -> Unit) {
        //TODO llama a FirebaseAuth.getInstance().signInWithEmailAndPassword(...) dentro de viewModelScope.launch, gestiona loading/error
    }

}