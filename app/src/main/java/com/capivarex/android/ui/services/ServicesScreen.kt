package com.capivarex.android.ui.services

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capivarex.android.ui.components.GlassCard
import com.capivarex.android.ui.theme.*

data class Capivara(
    val id: String,
    val name: String,
    val fullName: String,
    val description: String,
    val emoji: String,
    val color: androidx.compose.ui.graphics.Color,
    val isActive: Boolean = false,
    val isComingSoon: Boolean = true,
)

private val capivaras = listOf(
    Capivara("ara", "ARA", "Life & Time", "Your day, before it begins.", "🟡", Gold, isActive = true, isComingSoon = false),
    Capivara("ivi", "IVI", "Finance & Crypto", "Your financial intelligence.", "💚", Success),
    Capivara("oka", "OKA", "Home & IoT", "Your smart home command center.", "🔵", androidx.compose.ui.graphics.Color(0xFF3498DB)),
    Capivara("yara", "YARA", "Travel & Mobility", "Your travel companion.", "🟣", androidx.compose.ui.graphics.Color(0xFF9B59B6)),
    Capivara("ayvu", "AYVU", "Voice & Media", "Your voice and entertainment hub.", "🩷", androidx.compose.ui.graphics.Color(0xFFE91E8C)),
    Capivara("mbae", "MBAE", "Work & Productivity", "Your professional assistant.", "🟠", androidx.compose.ui.graphics.Color(0xFFE67E22)),
    Capivara("pora", "PORA", "Vision & Creative", "Your creative studio.", "🩵", androidx.compose.ui.graphics.Color(0xFF1ABC9C)),
    Capivara("tupa", "TUPA", "Security Intelligence", "Your invisible guardian.", "🔴", Error),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VoidBlack),
    ) {
        TopAppBar(
            title = {
                Column {
                    Text("Your Capivaras", fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text(
                        "Each Capivara awakens a new dimension of your AI.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted,
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = VoidBlack),
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(capivaras) { cap ->
                CapivaraCard(cap)
            }
        }
    }
}

@Composable
private fun CapivaraCard(cap: Capivara) {
    val alpha = if (cap.isActive) 1f else if (cap.isComingSoon) 0.45f else 0.7f

    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(alpha),
        padding = 14.dp,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
            // Emoji avatar
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(cap.color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center,
            ) {
                Text(cap.emoji, fontSize = 22.sp)
            }
            Spacer(Modifier.height(10.dp))

            // Name + badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(cap.name, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextPrimary)
                if (cap.isActive) {
                    Badge(
                        containerColor = cap.color.copy(alpha = 0.2f),
                        contentColor = cap.color,
                    ) {
                        Text("Active", fontSize = 9.sp, modifier = Modifier.padding(horizontal = 4.dp))
                    }
                } else if (cap.isComingSoon) {
                    Badge(
                        containerColor = Surface3,
                        contentColor = TextMuted,
                    ) {
                        Text("Soon", fontSize = 9.sp, modifier = Modifier.padding(horizontal = 4.dp))
                    }
                }
            }

            // Subtitle
            Text(
                cap.fullName,
                fontSize = 11.sp,
                color = TextMuted,
            )
            Spacer(Modifier.height(4.dp))

            // Description
            Text(
                cap.description,
                fontSize = 12.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp,
            )
        }
    }
}
