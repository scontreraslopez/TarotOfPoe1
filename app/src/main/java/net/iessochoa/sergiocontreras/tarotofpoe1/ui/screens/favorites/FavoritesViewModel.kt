package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository.CardRepository
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository.FavoritesRepository


class FavoritesViewModel(
    private val cardRepository: CardRepository,
    private val favoritesRepository: FavoritesRepository,
) : ViewModel() {

    private val allCards = MutableStateFlow<List<DivinationCard>>(emptyList())

    // Cruza el catálogo con los ids favoritos de Room: cuando se marca/desmarca
    // una carta, observeFavoriteIds() reemite y la lista se actualiza sola.
    val uiState: StateFlow<FavoritesUiState> =
        combine(allCards, favoritesRepository.observeFavoriteIds()) { cards, favoriteIds ->
            val favorites = favoriteIds.toSet()
            FavoritesUiState(
                cards = cards.filter { it.id in favorites },
                isLoading = false,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavoritesUiState(),
        )

    init {
        viewModelScope.launch { allCards.value = cardRepository.getCards() }
    }

    companion object {
        fun provideFactory(
            cardRepository: CardRepository,
            favoritesRepository: FavoritesRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                FavoritesViewModel(cardRepository, favoritesRepository) as T
        }
    }
}