package com.example.studypal

data class Flashcard(
    val id: Int,
    val question: String,
    val answer: String,
    var reviewCount: Int = 0
)