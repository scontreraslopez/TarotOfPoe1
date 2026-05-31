package net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login.LoginScreen
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login.LoginScreenUiState
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login.LoginViewModel

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation
 * Created by: Contr
 * On: 31/05/2026 at 19:37
 * Creado en Settings -> Editor -> File and Code Templates
 */

@Composable
fun NavigationWrapper() {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    //val repository = FirebaseBooksRepository(firestore)

    val backStack = rememberNavBackStack(Routes.Login)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeAt(backStack.lastIndex) },
        entryProvider = entryProvider {
            entry<Routes.Login> {
                LoginScreen(
                    uiState = LoginScreenUiState()
                )
            }


        }
    )
}
