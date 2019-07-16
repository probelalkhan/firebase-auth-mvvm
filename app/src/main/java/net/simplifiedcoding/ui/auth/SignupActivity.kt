package net.simplifiedcoding.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_signup.*
import net.simplifiedcoding.R
import net.simplifiedcoding.data.firebase.FirebaseSource
import net.simplifiedcoding.data.repositories.UserRepository
import net.simplifiedcoding.databinding.ActivitySignupBinding
import net.simplifiedcoding.ui.home.HomeActivity
import net.simplifiedcoding.utils.startHomeActivity

class SignupActivity : AppCompatActivity(), AuthListener {

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val firebaseSource = FirebaseSource()
        val repository = UserRepository(firebaseSource)
        val factory = AuthViewModelFactory(repository)

        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
        Intent(this, HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        startHomeActivity()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
