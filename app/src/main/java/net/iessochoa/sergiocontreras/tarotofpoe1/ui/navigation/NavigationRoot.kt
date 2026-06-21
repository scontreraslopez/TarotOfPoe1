package net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.google.firebase.auth.FirebaseAuth
import net.iessochoa.sergiocontreras.tarotofpoe1.data.repository.DummyCardRepository
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation.Routes.Detail
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation.Routes.Home
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation.Routes.Login
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.carddetail.CardDetailScreen
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.carddetail.CardDetailViewModel
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.home.HomeScreen
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login.LoginScreen
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login.LoginViewModel

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation
 * Created by: Contr
 * On: 31/05/2026
 */

@Composable
fun NavigationRoot(
) {

    // La forma moderna de inyectar dependencias, mejor que el factory
    val loginViewModel: LoginViewModel = viewModel {
        LoginViewModel(FirebaseAuth.getInstance())
    }

    val loginUiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    val repository = remember { DummyCardRepository() }
    val backStack = rememberNavBackStack(Login)

    /*
        Con Navigation3 ya no usamos el Scaffold
        Ahora cada pantalla controla su propio TopAppBar, FAB, BottomBar, etc
        Si en algún momento necesitas una bottom nav global (compartida entre varias pantallas), Navigation3 lo resuelve con Scenes
     */

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeAt(backStack.lastIndex) },
        entryProvider = entryProvider {
            entry<Login> {
                LoginScreen(
                    uiState = loginUiState,
                    onUsernameChange = loginViewModel::onUsernameChange, //Esto es lo mismo que{ loginViewModel.onUsernameChange(it) }
                    onPasswordChange = loginViewModel::onPasswordChange,
                    onUserLogin = { backStack.add(Home(loginUiState.username)) },
                    // TODO: Conectar onUserLogin en NavigationRoot para que llame a login() pasando el backStack como onSuccess
                    //TODO: Falta aquí gestionar el Firebase
                    onUserRegister = { /* TODO Handle registration logic */ },
                )
            }
            entry<Home> { key ->
                HomeScreen(
                    userName = key.name,
                    repository = repository,
                    onCardClick = { cardId -> backStack.add(Detail(cardId)) },
                )
            }
            entry<Detail> { key ->
                val cardDetailViewModel: CardDetailViewModel = viewModel(
                    key = key.cardId,
                    factory = CardDetailViewModel.provideFactory(repository, key.cardId)
                )

                val cardDetailUiState by cardDetailViewModel.uiState.collectAsStateWithLifecycle()

                CardDetailScreen(
                    uiState = cardDetailUiState,
                    onBack = { backStack.removeAt(backStack.lastIndex) },
                )
            }
        }
    )
}