package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.sergiocontreras.tarotofpoe1.R
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository.CardRepository
import net.iessochoa.sergiocontreras.tarotofpoe1.data.repository.DummyCardRepository
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.cardlist.CardListScreen
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.cardlist.CardListUiState
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.cardlist.CardListViewModel
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.TarotOfPoe1Theme

@Composable
fun HomeScreen(
    userName: String,
    repository: CardRepository,
    onCardClick: (String) -> Unit,
) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { destination ->
                item(
                    icon = {
                        Icon(
                            painterResource(destination.icon),
                            contentDescription = destination.label
                        )
                    },
                    label = { Text(destination.label) },
                    selected = destination == currentDestination,
                    onClick = { currentDestination = destination }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val contentModifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
            when (currentDestination) {
                AppDestinations.HOME -> {

                    //val listViewModel: CardListViewModel =
                    //  viewModel { CardListViewModel(repository) } Esto es trucazo no-factory

                    val listViewModel: CardListViewModel = viewModel(
                        factory = CardListViewModel.provideFactory(repository)
                    )

                    val listScreenUiState by listViewModel.uiState.collectAsStateWithLifecycle()

                    CardListScreen(
                        uiState = listScreenUiState,
                        onQueryChange = listViewModel::onQueryChange, // o { listViewModel.onQueryChange(it) }
                        onCardClick = onCardClick,
                        modifier = contentModifier
                    )
                }

                AppDestinations.FAVORITES -> Placeholder("Favorites", userName, contentModifier)
                AppDestinations.PROFILE -> Placeholder("Profile", userName, contentModifier)
            }
        }
    }
}

@Composable
private fun Placeholder(label: String, userName: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = "$label — $userName")
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("Home", R.drawable.ic_home),
    FAVORITES("Favorites", R.drawable.ic_favorite),
    PROFILE("Profile", R.drawable.ic_account_box),
}

@Preview
@Composable
private fun HomeScreenPreview() {
    TarotOfPoe1Theme {
        HomeScreen(
            userName = "Sergio",
            repository = DummyCardRepository(),
            onCardClick = {},
        )
    }
}