package com.datn.mobileapp.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.datn.mobileapp.R
import com.datn.mobileapp.databinding.FragmentDashboardBinding
import com.datn.mobileapp.utils.BaseFragment
import com.datn.mobileapp.utils.viewBindings
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment(R.layout.fragment_dashboard) {
    private val dashboardViewModel: DashboardViewModel by viewModel()
    private val viewBinding by viewBindings(FragmentDashboardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            viewBinding.textDashboard.text = it
        })
    }
}