package com.silvozatechnologies.kitsuandroid.ui.signin.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.silvozatechnologies.kitsuandroid.R
import com.silvozatechnologies.kitsuandroid.ui.home.view.activity.HomeActivity
import com.silvozatechnologies.kitsuandroid.ui.signin.viewmodel.SignInViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initializeViewModel()
        initializeButtons()
        startObserving()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    private fun initializeButtons() {
        button_sign_in.setOnClickListener { onSignInButtonClicked() }
    }

    private fun startObserving() {
        viewModel.flow.observe(this, Observer { onFlowChanged(it) })
    }

    private fun onSignInButtonClicked() {
        val username = edittext_username.text.toString()
        val password = edittext_password.text.toString()
        viewModel.onSignInClicked(username, password)
    }

    private fun onFlowChanged(flow: Int?) {
        flow?.let {
            when(flow) {
                SignInViewModel.FLOW_TO_HOME -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
