package com.capivarex.android.ui.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capivarex.android.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen() {
    Scaffold(
        containerColor = VoidBlack,
        topBar = {
            TopAppBar(
                title = { Text("Notes", fontWeight = FontWeight.Bold, color = TextPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VoidBlack),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: create note */ },
                containerColor = Gold,
                contentColor = VoidBlack,
            ) {
                Icon(Icons.Default.Add, contentDescription = "New note")
            }
        },
    ) { padding ->
        // Empty state
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(VoidBlack),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                Icons.Default.NoteAlt,
                contentDescription = null,
                tint = TextMuted.copy(alpha = 0.3f),
                modifier = Modifier.size(64.dp),
            )
            Spacer(Modifier.height(16.dp))
            Text("No notes yet", style = MaterialTheme.typography.titleMedium, color = TextMuted)
            Spacer(Modifier.height(8.dp))
            Text(
                "Tap + to create your first note,\nor just tell ARA in the chat.",
                style = MaterialTheme.typography.bodySmall,
                color = TextMuted.copy(alpha = 0.6f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            )
        }
    }
}
