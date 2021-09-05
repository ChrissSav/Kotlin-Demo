package com.example.android_kotlin_demo.ui.register

import android.app.Activity
import android.content.Intent
import android.view.View
import com.example.android_kotlin_demo.data.base.BaseActivityViewModel
import com.example.android_kotlin_demo.databinding.ActivityRegisterBinding
import com.example.android_kotlin_demo.util.createAlerter
import com.example.android_kotlin_demo.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterActivity : BaseActivityViewModel<ActivityRegisterBinding, RegisterViewModel>(RegisterViewModel::class.java) {

    override fun provideViewBinding(): ActivityRegisterBinding =
        ActivityRegisterBinding.inflate(layoutInflater)

    override fun setUpObservers() {
        viewModel.userDetails.observe(this, {
            val resultIntent = Intent()
            resultIntent.putExtra("user", it)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        })

        viewModel.error.observe(this){
            if (it.internal)
                createAlerter(getString(it.messageId))
            else
                createAlerter(it.message)
        }

        viewModel.load.observe(this, {
            if (it)
                binding.genericLoader.root.visibility = View.VISIBLE
            else
                binding.genericLoader.root.visibility = View.INVISIBLE

        })
    }

    override fun setUpViews() {

        with(binding){
            registerButton.setOnClickListener {
                hideKeyboard()
                if(emailTextView.isCorrect() and passwordTextView.isCorrect()){
                    val email = binding.emailTextView.text.toString()
                    val password = binding.passwordTextView.text.toString()
                    viewModel.registerUser(email,password)
                }
            }
        }

    }
}