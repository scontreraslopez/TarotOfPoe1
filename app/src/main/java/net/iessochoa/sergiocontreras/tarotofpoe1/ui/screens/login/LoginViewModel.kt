package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login
 * Created by: Contr
 * On: 23/05/2026 at 21:59
 * Creado en Settings -> Editor -> File and Code Templates
 */

class LoginViewModel(
    private val auth: FirebaseAuth
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginScreenUiState())
    val uiState: StateFlow<LoginScreenUiState> = _uiState.asStateFlow()

    fun onUsernameChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                username = value,
                isLoginEnabled = value.isNotBlank() && currentState.password.isNotBlank(),
                isLoginError = false // Al escribir se cancela el error
            )
        }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = value,
                isLoginEnabled = currentState.username.isNotBlank() && value.isNotBlank(),
                isLoginError = false // Al escribir se cancela el error
            )
        }
    }

    // ESTADO FIREBASE LOGIN:
    // ✓ FirebaseAuth inyectado via lambda factory en NavigationRoot
    // ✓ signInWithEmailAndPassword conectado con username/password del UiState
    // ✓ isLoading activado antes de la llamada y desactivado al completar
    // TODO: Usar isLoading en LoginScreen para deshabilitar botones o mostrar CircularProgressIndicator mientras Firebase responde
    // ✓ onSuccess invocado con el email del usuario logado
    // ✓ isLoginError actualizado en UiState cuando falla
    // ✓ onUserLogin conectado en NavigationRoot llamando a login() con email real de Firebase
    // ✓ registerUser() implementado con auth.createUserWithEmailAndPassword
    // ✓ isLoginError mostrado en la UI con supportingText en PasswordField
    // ✓ isLoginError reseteado a false al escribir en username o password
    fun login(onSuccess: (String) -> Unit) {

        _uiState.update { it.copy(isLoading = true) }

        val username = _uiState.value.username
        val password = _uiState.value.password

        viewModelScope.launch {
        // Lo hemos movido a la corutina, la respuesta de firebase es un evento asíncrono
            auth.signInWithEmailAndPassword(
                username,
                password
            ).addOnCompleteListener { task ->
                _uiState.update { it.copy(isLoading = false) }
                if(task.isSuccessful) {
                    Log.i("Login button", "User logged: ${auth.currentUser?.email}")
                    onSuccess(auth.currentUser?.email ?: _uiState.value.username)
                } else {
                    Log.i("Login button", "User login failed: ${task.exception.toString()}")
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoginError = true
                        )
                    }
                }
            }


        }

    }



    // ✓ onSuccess recibe el email del usuario registrado y se invoca al completar
    // ✓ isLoginError actualizado en UiState cuando el registro falla
    // ✓ onUserRegister conectado en NavigationRoot llamando a registerUser() con email real de Firebase
    fun registerUser(onSuccess: (String) -> Unit) {

        _uiState.update { it.copy(isLoading = true) }

        val username = _uiState.value.username
        val password = _uiState.value.password

        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(
                username,
                password
            ).addOnCompleteListener { task ->
                _uiState.update { it.copy(isLoading = false) }
                if(task.isSuccessful) {
                    Log.i(
                        "Register button", "User registered with ID: ${auth.currentUser?.uid}"
                    )
                    onSuccess(auth.currentUser?.email ?: username)
                } else {
                    Log.i("Register button", "User registration failed: ${task.exception.toString()}")
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoginError = true
                        )
                    }
                }
            }

        }
    }
}