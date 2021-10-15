package com.buupass.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import com.buupass.test.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var factory: VMFactory
    private val viewModel: MainViewModel by viewModels { factory }
    private lateinit var binding: ActivityMainBinding
    private lateinit var tilName: TextInputLayout
    private lateinit var tieName: TextInputEditText
    private lateinit var tilPassword: TextInputLayout
    private lateinit var tiePassword: TextInputEditText
    private lateinit var textForgotPassword: TextView
    private lateinit var buttonLogin: AppCompatButton
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as Test).applicationComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        tilName = binding.tilName
        tieName = binding.tieName
        tilPassword = binding.tilPassword
        tiePassword = binding.tiePassword
        textForgotPassword = binding.forgotPassword
        buttonLogin = binding.login
        progressBar = binding.progressCircular
        setObservers()
        setOnClicks()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposable.dispose()
    }

    private fun setObservers() {

        viewModel.snackBarMessage.observe(this, {
            Snackbar.make(binding.root, getString(it), Snackbar.LENGTH_SHORT).apply {
                setAction(getString(R.string.dismiss)) { _ ->
                    dismiss()
                }
                show()
            }
        })

        viewModel.toastMessage.observe(this, {
            if (!it.isNullOrBlank()) Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.showLoading.observe(this, {
            progressBar.visibility = when(it) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        })

        viewModel.loginSuccessful.observe(this, {
            if (it) {
                viewModel.toastMessage.value = getString(R.string.login_success)
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        })
    }

    private fun setOnClicks(){
        textForgotPassword.setOnClickListener {
            viewModel.resetPassword()
        }
        buttonLogin.setOnClickListener {
            val name = tieName.text.toString()
            val password = tiePassword.text.toString()
            if (validateInput(name, password)) viewModel.login(name, password)
        }
    }

    private fun validateInput(name: String?, password: String?): Boolean {
        if (name.isNullOrBlank()) {
            tilName.error = getString(R.string.name_required)
            return false
        }
        tilName.error = null
        if (password.isNullOrBlank()) {
            tilPassword.error = getString(R.string.password_required)
            return false
        }
        tilPassword.error = null
        return true
    }

}