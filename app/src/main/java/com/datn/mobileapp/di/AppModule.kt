package com.datn.mobileapp.di

import com.datn.mobileapp.data.cache.AppSharedPreferences
import com.datn.mobileapp.data.repository.AuthRepositoryImpl
import com.datn.mobileapp.domain.repository.AuthRepository
import com.datn.mobileapp.ui.dashboard.DashboardViewModel
import com.datn.mobileapp.ui.home.HomeViewModel
import com.datn.mobileapp.ui.login.LoginViewModel
import com.datn.mobileapp.ui.notifications.NotificationsViewModel
import com.datn.mobileapp.ui.profile.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { DashboardViewModel() }
    viewModel { NotificationsViewModel() }
    viewModel { ProfileViewModel(get()) }
    viewModel { LoginViewModel() }
}

val serviceModule = module {
    single { FirebaseAuth.getInstance() }
    single { AppSharedPreferences(androidContext()) }
    single { FirebaseFirestore.getInstance() }
}

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
}