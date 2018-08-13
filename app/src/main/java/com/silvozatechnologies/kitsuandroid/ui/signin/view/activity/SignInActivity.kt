package com.silvozatechnologies.kitsuandroid.ui.signin.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
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
        viewModel.spiel.observe(this, Observer { onSpielChanged(it) })
    }

    private fun onSignInButtonClicked() {
        val username = edittext_username.text.toString()
        val password = edittext_password.text.toString()
        viewModel.onSignInClicked(username, password)
    }

    private fun onFlowChanged(flow: Int?) {
        flow?.let {
            when (flow) {
                SignInViewModel.FLOW_TO_HOME -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun onSpielChanged(spiel: Int?) {
        val spielString = spiel?.let {
            when (spiel) {
                SignInViewModel.SPIEL_ERROR_USERNAME_PASSWORD_NOT_EMPTY ->
                    resources.getString(R.string.spiel_error_username_password_not_empty)
                SignInViewModel.SPIEL_ERROR_INVALID_USERNAME_PASSWORD ->
                    resources.getString(R.string.spiel_error_invalid_username_password)
                else ->
                    resources.getString(R.string.spiel_error_default)
            }
        }
        spielString?.let {
            Snackbar.make(layout, it, Snackbar.LENGTH_SHORT).show()
        }
    }
}
