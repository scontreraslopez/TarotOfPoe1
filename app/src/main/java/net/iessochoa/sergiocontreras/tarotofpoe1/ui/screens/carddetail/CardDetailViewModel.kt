package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.carddetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository.CardRepository
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository.FavoritesRepository


class CardDetailViewModel(
    private val repository: CardRepository,
    private val favoritesRepository: FavoritesRepository,
    private val cardId: String,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardDetailUiState())
    val uiState: StateFlow<CardDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val card = repository.getCard(cardId)
            _uiState.update { it.copy(card = card, isLoading = false) }
        }
        // Observa el favorito de Room y lo refleja en el UiState en tiempo real.
        viewModelScope.launch {
            favoritesRepository.observeIsFavorite(cardId).collect { favorite ->
                _uiState.update { it.copy(isFavorite = favorite) }
            }
        }
    }

    /** Marca / desmarca la carta como favorita; Room reemite y actualiza el UiState. */
    fun toggleFavorite() {
        viewModelScope.launch {
            favoritesRepository.setFavorite(cardId, !_uiState.value.isFavorite)
        }
    }

    // TODO: Migrar a lambda factory. Eliminar este companion object y en NavigationRoot sustituir
    //  factory = CardDetailViewModel.provideFactory(repository, favoritesRepository, key.cardId)
    //  por: viewModel(key = key.cardId) { CardDetailViewModel(repository, favoritesRepository, key.cardId) }
    companion object {
        fun provideFactory(
            repository: CardRepository,
            favoritesRepository: FavoritesRepository,
            cardId: String,
        ): ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel> create(modelClass: Class<T>): T =
                CardDetailViewModel(repository, favoritesRepository, cardId) as T
        }
    }
}