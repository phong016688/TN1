package com.datn.mobileapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.datn.mobileapp.domain.repository.AuthRepository
import com.datn.mobileapp.ui.profile.ProfileContract.ProfileAction
import com.datn.mobileapp.ui.profile.ProfileContract.ProfileState
import com.datn.mobileapp.utils.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class ProfileViewModel(private val authRepo: AuthRepository) : ViewModel() {
    private val mutableLiveData = MutableLiveData<ProfileState>()
    val liveData: LiveData<ProfileState> = mutableLiveData
    private val mutableLiveAction = SingleLiveEvent<ProfileAction>()
    private val actionObserver = Observer<ProfileAction> { action ->
        when (action) {
            is ProfileAction.GetProfile -> getProfile(action.userId)
            else -> Unit
        }
    }
    private val compositeDisposable = CompositeDisposable()

    init {
        mutableLiveAction.observeForever(actionObserver)
        mutableLiveAction.value = ProfileAction.GetProfile("OpvubxH51McFPzW3vDug")
        mutableLiveData.value = ProfileState(null, true)
    }

    override fun onCleared() {
        mutableLiveAction.call()
        mutableLiveAction.removeObserver(actionObserver)
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun getProfile(userId: String) {
        authRepo.getUserProfile(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val valuePre = mutableLiveData.value ?: return@subscribe
                mutableLiveData.value = valuePre.copy(user = it, isLoading = false)
            }, {
                val valuePre = mutableLiveData.value ?: return@subscribe
                mutableLiveData.value = valuePre.copy(user = null, isLoading = false)
            })
            .addTo(compositeDisposable)

    }
}