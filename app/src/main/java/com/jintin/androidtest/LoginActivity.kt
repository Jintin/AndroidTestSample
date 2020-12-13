package com.jintin.androidtest

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jintin.androidtest.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: LoginViewModel.LoginViewModelFactory
    private val viewModel: LoginViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityLoginBinding.inflate(layoutInflater)
                .apply {
                    bindView()
                    setContentView(root)
                }
    }

    private fun ActivityLoginBinding.bindView() {
        submit.setOnClickListener {
            viewModel.login(account.text.toString(), password.text.toString())
        }
        viewModel.liveData.observe(this@LoginActivity) { success ->
            status.setText(if (success) {
                R.string.login_success
            } else {
                R.string.login_fail
            })
        }
    }
}