package br.com.levpatrim.view.home.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.levpatrim.model.repositories.UserRepository

class ScreenUserViewModelFactory (private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScreenUserViewModel(repository) as T
    }

}