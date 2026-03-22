package com.capivarex.android.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Note
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Route(val path: String) {
    data object Login : Route("login")
    data object Register : Route("register")
    data object Chat : Route("chat")
    data object Services : Route("services")
    data object Notes : Route("notes")
    data object Settings : Route("settings")
}

data class BottomNavItem(
    val route: Route,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(Route.Chat, "Chat", Icons.Filled.Chat, Icons.Outlined.Chat),
    BottomNavItem(Route.Services, "Services", Icons.Filled.Home, Icons.Outlined.Home),
    BottomNavItem(Route.Notes, "Notes", Icons.Filled.Note, Icons.Outlined.Note),
    BottomNavItem(Route.Settings, "Settings", Icons.Filled.Settings, Icons.Outlined.Settings),
)
