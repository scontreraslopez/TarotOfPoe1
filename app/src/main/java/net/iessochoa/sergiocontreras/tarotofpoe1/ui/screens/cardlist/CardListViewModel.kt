package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.cardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.tarotofpoe1.data.repository.CardRepository
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.carddetail.CardDetailViewModel


class CardListViewModel(
    private val repository: CardRepository,
) : ViewModel() {

    private val allCards = MutableStateFlow<List<DivinationCard>>(emptyList())
    private val query = MutableStateFlow("")

    val uiState: StateFlow<CardListUiState> =
        combine(allCards, query) { cards, query ->
            CardListUiState(
                query = query,
                cards = cards.filter { it.matches(query) },
                isLoading = false,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CardListUiState(),
        )

    init {
        viewModelScope.launch { allCards.value = repository.getCards() }
    }

    fun onQueryChange(newQuery: String) {
        query.value = newQuery
    }

    /** Matches by card name OR by any area it drops in. Blank query matches everything. */
    private fun DivinationCard.matches(query: String): Boolean {
        if (query.isBlank()) return true
        val q = query.trim()
        return name.contains(q, ignoreCase = true) ||
            acquisition.areaRestrictions.any { it.contains(q, ignoreCase = true) }
    }

    // Para inyección de dependencias manual este companion object es el de siempre
    companion object {
        fun provideFactory(
            repository: CardRepository,
        ): ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel> create(modelClass: Class<T>): T =
                CardListViewModel(repository) as T
        }
    }
}