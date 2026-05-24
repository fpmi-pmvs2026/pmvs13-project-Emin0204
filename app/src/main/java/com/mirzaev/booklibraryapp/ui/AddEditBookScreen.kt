package com.mirzaev.booklibraryapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mirzaev.booklibraryapp.data.BookEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditBookScreen(
    bookId: Int?,
    onNavigateBack: () -> Unit,
    viewModel: BookViewModel
) {
    val books by viewModel.books.collectAsState()
    val existingBook = if (bookId != null && bookId != 0) {
        books.find { it.id == bookId }
    } else null

    var title by remember { mutableStateOf(TextFieldValue(existingBook?.title ?: "")) }
    var author by remember { mutableStateOf(TextFieldValue(existingBook?.author ?: "")) }
    var genre by remember { mutableStateOf(TextFieldValue(existingBook?.genre ?: "")) }
    var isRead by remember { mutableStateOf(existingBook?.isRead ?: false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(if (existingBook == null) "Добавить книгу" else "Редактировать книгу") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Название") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Автор") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = genre,
                onValueChange = { genre = it },
                label = { Text("Жанр") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isRead,
                    onCheckedChange = { isRead = it }
                )
                Text("Прочитано")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val book = BookEntity(
                        id = existingBook?.id ?: 0,
                        title = title.text,
                        author = author.text,
                        genre = genre.text,
                        isRead = isRead
                    )
                    if (existingBook == null) {
                        viewModel.addBook(book)
                    } else {
                        viewModel.updateBook(book)
                    }
                    onNavigateBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (existingBook == null) "Сохранить" else "Обновить")
            }
        }
    }
}