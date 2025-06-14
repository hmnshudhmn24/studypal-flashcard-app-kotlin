package com.example.studypal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FlashcardScreen(viewModel: FlashcardViewModel = viewModel()) {
    var showAnswer by remember { mutableStateOf(false) }
    var newQuestion by remember { mutableStateOf("") }
    var newAnswer by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("StudyPal - Adaptive Flashcards", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.cards.size) { index ->
                val card = viewModel.cards[index]
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        showAnswer = !showAnswer
                        viewModel.incrementReview(card)
                    }) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Q: ${card.question}", style = MaterialTheme.typography.bodyLarge)
                        if (showAnswer) Text("A: ${card.answer}", color = Color.Gray)
                        Text("Reviewed: ${card.reviewCount} times", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                BasicTextField(
                    value = newQuestion,
                    onValueChange = { newQuestion = it },
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(8.dp)
                        .fillMaxWidth(),
                    decorationBox = { innerTextField -> innerTextField() }
                )
                Spacer(modifier = Modifier.height(4.dp))
                BasicTextField(
                    value = newAnswer,
                    onValueChange = { newAnswer = it },
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(8.dp)
                        .fillMaxWidth(),
                    decorationBox = { innerTextField -> innerTextField() }
                )
            }
            Button(onClick = {
                if (newQuestion.isNotBlank() && newAnswer.isNotBlank()) {
                    viewModel.addCard(newQuestion, newAnswer)
                    newQuestion = ""
                    newAnswer = ""
                }
            }) {
                Text("Add")
            }
        }
    }
}