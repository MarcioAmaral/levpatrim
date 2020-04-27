package br.com.levpatrim.view.home.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.levpatrim.model.repositories.UserRepository
import br.com.levpatrim.viewmodel.AuthViewModel
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

class ProfileViewModelFactory (private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }

}