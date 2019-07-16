package net.simplifiedcoding.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import net.simplifiedcoding.R
import net.simplifiedcoding.data.firebase.FirebaseSource
import net.simplifiedcoding.data.repositories.UserRepository
import net.simplifiedcoding.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val firebaseSource = FirebaseSource()
        val repository = UserRepository(firebaseSource)
        val factory = HomeViewModelFactory(repository)

        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        binding.viewmodel = viewModel

    }

}
