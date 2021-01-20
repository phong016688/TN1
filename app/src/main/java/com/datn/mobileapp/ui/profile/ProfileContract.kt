package com.datn.mobileapp.ui.profile

import com.datn.mobileapp.domain.model.User

object ProfileContract {
    data class ProfileState(
        val user: User? = null,
        val isLoading: Boolean = false,
    )

    sealed class ProfileAction() {
        class GetProfile(val userId: String): ProfileAction()
    }
}