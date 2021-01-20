package com.datn.mobileapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.datn.mobileapp.R
import com.datn.mobileapp.databinding.FragmentHomeBinding
import com.datn.mobileapp.utils.BaseFragment
import com.datn.mobileapp.utils.viewBindings
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by viewModel()
    private val viewBinding by viewBindings(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.text.observe(viewLifecycleOwner, {
            viewBinding.textHome.text = it
        })
    }
}