package net.simplifiedcoding.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import net.simplifiedcoding.R
import net.simplifiedcoding.data.firebase.FirebaseSource
import net.simplifiedcoding.data.repositories.UserRepository
import net.simplifiedcoding.databinding.ActivityLoginBinding
import net.simplifiedcoding.utils.startHomeActivity


class LoginActivity : AppCompatActivity(), AuthListener {

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firebaseSource = FirebaseSource()
        val repository = UserRepository(firebaseSource)
        val factory = AuthViewModelFactory(repository)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        startHomeActivity()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        viewModel.user?.let {
            startHomeActivity()
        }
    }
}
