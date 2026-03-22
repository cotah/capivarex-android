package com.capivarex.android.ui

import androidx.lifecycle.ViewModel
import com.capivarex.android.data.auth.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppRootViewModel @Inject constructor(
    val authManager: AuthManager,
) : ViewModel()
