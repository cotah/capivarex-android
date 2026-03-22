package com.capivarex.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.*
import com.capivarex.android.data.auth.AuthManager
import com.capivarex.android.ui.auth.LoginScreen
import com.capivarex.android.ui.auth.RegisterScreen
import com.capivarex.android.ui.chat.ChatScreen
import com.capivarex.android.ui.navigation.*
import com.capivarex.android.ui.notes.NotesScreen
import com.capivarex.android.ui.services.ServicesScreen
import com.capivarex.android.ui.settings.SettingsScreen
import com.capivarex.android.ui.theme.GlassBorder
import com.capivarex.android.ui.theme.Gold
import com.capivarex.android.ui.theme.Surface1
import com.capivarex.android.ui.theme.TextMuted
import com.capivarex.android.ui.theme.VoidBlack
import javax.inject.Inject

@Composable
fun CapivarexAppRoot() {
    val authManager: AuthManager = hiltViewModel<AppRootViewModel>().authManager
    val token by authManager.token.collectAsState(initial = null)
    val isLoggedIn = token != null

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(VoidBlack),
        containerColor = VoidBlack,
        bottomBar = {
            if (isLoggedIn) {
                CapivarexBottomBar(navController)
            }
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) Route.Chat.path else Route.Login.path,
            modifier = Modifier.padding(padding),
        ) {
            // Auth
            composable(Route.Login.path) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Route.Chat.path) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    onNavigateRegister = {
                        navController.navigate(Route.Register.path)
                    },
                )
            }
            composable(Route.Register.path) {
                RegisterScreen(
                    onRegisterSuccess = {
                        navController.navigate(Route.Chat.path) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    onNavigateLogin = { navController.popBackStack() },
                )
            }

            // Main tabs
            composable(Route.Chat.path) { ChatScreen() }
            composable(Route.Services.path) { ServicesScreen() }
            composable(Route.Notes.path) { NotesScreen() }
            composable(Route.Settings.path) {
                SettingsScreen(
                    onLogout = {
                        navController.navigate(Route.Login.path) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun CapivarexBottomBar(navController: androidx.navigation.NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Surface1,
        tonalElevation = 0.dp,
        modifier = Modifier.height(64.dp),
    ) {
        bottomNavItems.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route.path } == true
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route.path) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label,
                        modifier = Modifier.size(20.dp),
                    )
                },
                label = {
                    Text(item.label, style = MaterialTheme.typography.bodySmall)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Gold,
                    selectedTextColor = Gold,
                    unselectedIconColor = TextMuted,
                    unselectedTextColor = TextMuted,
                    indicatorColor = Gold.copy(alpha = 0.1f),
                ),
            )
        }
    }
}
