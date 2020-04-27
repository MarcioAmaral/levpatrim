package br.com.levpatrim.view.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.levpatrim.model.repositories.UserRepository
import br.com.levpatrim.viewmodel.AuthViewModel
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

class AuthViewModelFactory (private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }

}