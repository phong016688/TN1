package com.datn.mobileapp.di

import com.datn.mobileapp.data.repository.AuthRepositoryImpl
import com.datn.mobileapp.ui.dashboard.DashboardViewModel
import com.datn.mobileapp.ui.home.HomeViewModel
import com.datn.mobileapp.ui.login.LoginViewModel
import com.datn.mobileapp.ui.notifications.NotificationsViewModel
import com.datn.mobileapp.ui.profile.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { DashboardViewModel() }
    viewModel { NotificationsViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { LoginViewModel() }
}

val serviceModel = module {
    single { FirebaseAuth.getInstance() }
}

val repositoryModule = module {
    single { AuthRepositoryImpl(get()) }
}