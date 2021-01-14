package com.datn.mobileapp.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.datn.mobileapp.R
import com.datn.mobileapp.databinding.FragmentNotificationsBinding
import com.datn.mobileapp.utils.BaseFragment
import com.datn.mobileapp.utils.viewBindings
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : BaseFragment(R.layout.fragment_notifications) {
    private val notificationsViewModel: NotificationsViewModel by viewModel()
    private val viewBiding by viewBindings(FragmentNotificationsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer{
            viewBiding.textNotifications.text = it
        })
    }
}