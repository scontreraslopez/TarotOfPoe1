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

    // ESTADO FIREBASE LOGIN:
    // ✓ FirebaseAuth inyectado via lambda factory en NavigationRoot
    // ✓ signInWithEmailAndPassword conectado con username/password del UiState
    // ✓ isLoading activado antes de la llamada y desactivado al completar
    // ✓ onSuccess invocado con el email del usuario logado
    // ✓ isLoginError actualizado en UiState cuando falla
    // ✓ onUserLogin conectado en NavigationRoot llamando a login() con email real de Firebase
    // TODO: Implementar registerUser() con auth.createUserWithEmailAndPassword (ver código comentado al final)
    // TODO: Mostrar el error isLoginError en la UI (LoginScreen) con un mensaje o snackbar
    // TODO: Resetear isLoginError a false cuando el usuario empiece a escribir de nuevo
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



    /*


    fun registerUser() {
        auth.createUserWithEmailAndPassword(
            _userName.value.toString(),
            _password.value.toString()
        ).addOnCompleteListener { result ->
            Log.i(
                "Register button",
                if (result.isSuccessful)
                    "User registered with ID: ${auth.currentUser?.uid}"
                else
                    "Registry failed ${result.exception.toString()}"
            )
        }
    }

    fun loginUser() {
        auth.signInWithEmailAndPassword(
            _userName.value.toString(),
            _password.value.toString()
        ).addOnCompleteListener {
            if(it.isSuccessful) {
                Log.i("Login button", "User logged: ${auth.currentUser?.email}")
                navigateToHome(_userName.value.toString())
            } else {
                Log.i("Login button", "User login failed: ${it.exception.toString()}")
                _isLoginError.value = true
            }
        }
    }


     */
}