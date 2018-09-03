package com.silvozatechnologies.kitsuandroid.ui.signin.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.silvozatechnologies.kitsuandroid.R
import com.silvozatechnologies.kitsuandroid.ui.home.view.activity.HomeActivity
import com.silvozatechnologies.kitsuandroid.ui.signin.viewmodel.SignInViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_signin.*
import java.util.*

class SignInActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SignInViewModel

    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        initializeFacebook()
        initializeViewModel()
        initializeButtons()
        startObserving()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private fun initializeFacebook() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                result?.let {
                    viewModel.onFacebookLoginSuccess(result.accessToken.token)
                }
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException?) {
                viewModel.onFacebookLoginError()
            }
        })
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    private fun initializeButtons() {
        button_sign_in.setOnClickListener { onSignInButtonClicked() }
        button_facebook_sign_in.setReadPermissions(Arrays.asList("public_profile", "email"))

        // Only use dummy account to prevent pushing real account
        edittext_username.setText("kimtestemail1@gmail.com")
        edittext_password.setText("testpassword001")
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
