package com.datn.mobileapp.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import com.datn.mobileapp.R
import com.datn.mobileapp.databinding.FragmentProfileBinding
import com.datn.mobileapp.utils.BaseFragment
import com.datn.mobileapp.utils.viewBindings
import com.phong.lib.LoadingDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentProfile : BaseFragment(R.layout.fragment_profile) {
    private val viewBinding by viewBindings(FragmentProfileBinding::bind)
    private var loadingDialog: LoadingDialog? = null
    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog()
    }

    override fun onResume() {
        super.onResume()
        profileViewModel.liveData.observe(viewLifecycleOwner, { state ->
            if (state.user != null) {
                viewBinding.nameEditText.setText(state.user.fullName)
                viewBinding.avatarImageView.loadUrl(state.user.avatar)
                viewBinding.emailTextView.text = state.user.mEmail
            }
        })
    }

    companion object {
        fun newInstance() = FragmentProfile()
    }
}