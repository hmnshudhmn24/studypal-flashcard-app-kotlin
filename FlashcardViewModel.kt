package com.example.studypal

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class FlashcardViewModel : ViewModel() {
    private var _cards = mutableStateListOf(
        Flashcard(1, "What is Kotlin?", "A modern programming language for Android"),
        Flashcard(2, "What is Room?", "A SQLite object mapping library"),
        Flashcard(3, "What is Jetpack Compose?", "A modern toolkit for building native UI")
    )
    val cards: List<Flashcard> get() = _cards

    fun incrementReview(card: Flashcard) {
        val index = _cards.indexOfFirst { it.id == card.id }
        if (index != -1) _cards[index] = _cards[index].copy(reviewCount = _cards[index].reviewCount + 1)
    }

    fun addCard(question: String, answer: String) {
        val id = (_cards.maxOfOrNull { it.id } ?: 0) + 1
        _cards.add(Flashcard(id, question, answer))
    }
}