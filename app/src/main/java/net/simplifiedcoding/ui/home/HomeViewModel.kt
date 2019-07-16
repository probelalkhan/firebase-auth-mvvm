package net.simplifiedcoding.ui.home

import android.view.View
import androidx.lifecycle.ViewModel
import net.simplifiedcoding.data.repositories.UserRepository
import net.simplifiedcoding.utils.startLoginActivity

class HomeViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }
    
    fun logout(view: View){
        repository.logout()
        view.context.startLoginActivity()
    }
}