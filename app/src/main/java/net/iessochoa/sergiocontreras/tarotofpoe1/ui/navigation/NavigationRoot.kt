package net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import net.iessochoa.sergiocontreras.tarotofpoe1.data.repository.DummyCardRepository
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation.Routes.Detail
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation.Routes.Home
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation.Routes.Login
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.carddetail.CardDetailScreen
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.carddetail.CardDetailViewModel
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.home.HomeScreen
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login.LoginScreen
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.login.LoginScreenUiState

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation
 * Created by: Contr
 * On: 31/05/2026
 */

@Composable
fun NavigationRoot() {
    val repository = remember { DummyCardRepository() }
    val backStack = rememberNavBackStack(Login)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeAt(backStack.lastIndex) },
        entryProvider = entryProvider {
            entry<Login> {
                LoginScreen(
                    uiState = LoginScreenUiState(),
                    onLoginSuccess = { userName -> backStack.add(Home(userName)) }
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
                CardDetailScreen(
                    viewModel = viewModel(key = key.cardId) {
                        CardDetailViewModel(repository, key.cardId)
                    },
                    onBack = { backStack.removeAt(backStack.lastIndex) },
                )
            }
        }
    )
}