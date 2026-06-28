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
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository.FavoritesRepository
import net.iessochoa.sergiocontreras.tarotofpoe1.data.repository.DummyCardRepository
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.cardlist.CardListScreen
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.cardlist.CardListUiState
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.cardlist.CardListViewModel
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.favorites.FavoritesScreen
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.favorites.FavoritesViewModel
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.TarotOfPoe1Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    userName: String,
    repository: CardRepository,
    favoritesRepository: FavoritesRepository,
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

                AppDestinations.FAVORITES -> {
                    val favoritesViewModel: FavoritesViewModel = viewModel(
                        factory = FavoritesViewModel.provideFactory(repository, favoritesRepository)
                    )
                    val favoritesUiState by favoritesViewModel.uiState.collectAsStateWithLifecycle()

                    FavoritesScreen(
                        uiState = favoritesUiState,
                        onCardClick = onCardClick,
                        modifier = contentModifier,
                    )
                }
                // TODO: implementar la pantalla de Profile (perfil + logout).
                //  - Mostrar datos del usuario (email/nombre de FirebaseAuth.currentUser).
                //  - Botón de logout: FirebaseAuth.getInstance().signOut() y navegar de vuelta a Login.
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
    // Fake en memoria para el preview; en la app real es RoomFavoritesRepository.
    val previewFavorites = object : FavoritesRepository {
        override fun observeFavoriteIds(): Flow<List<String>> = flowOf(emptyList())
        override fun observeIsFavorite(cardId: String): Flow<Boolean> = flowOf(false)
        override suspend fun setFavorite(cardId: String, favorite: Boolean) {}
    }
    TarotOfPoe1Theme {
        HomeScreen(
            userName = "Sergio",
            repository = DummyCardRepository(),
            favoritesRepository = previewFavorites,
            onCardClick = {},
        )
    }
}