package com.example.android_kotlin_demo.ui.fragments

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.example.android_kotlin_demo.data.base.BaseFragment
import com.example.android_kotlin_demo.databinding.FragmentProfileBinding
import com.example.android_kotlin_demo.domain.UserDetails
import com.example.android_kotlin_demo.ui.MainViewModel
import com.example.android_kotlin_demo.ui.register.RegisterActivity
import com.example.android_kotlin_demo.util.loadImageFromUrl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun getViewBinding(): FragmentProfileBinding =
        FragmentProfileBinding.inflate(layoutInflater)

    override fun setUpObservers() {

        viewModel.userDetails.observe(viewLifecycleOwner, { user ->
            with(binding) {
                if (user != null) {
                    firstNameTextView.text = user.firstName
                    lastNameTextView.text = user.lastName
                    userImg.loadImageFromUrl(user.picture)
                    detailsLayout.visibility = View.VISIBLE
                    registerButton.visibility = View.GONE
                } else {
                    detailsLayout.visibility = View.GONE
                    registerButton.visibility = View.VISIBLE
                }
            }

        })
    }

    override fun setUpViews() {

        binding.registerButton.setOnClickListener {
            val intent = Intent(activity, RegisterActivity::class.java)
            resultLauncher.launch(intent)
        }


        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val user = data?.getParcelableExtra<UserDetails>("user")
                user?.let {
                    viewModel.settUserDetails(it)
                }
            }
        }

    }

}