package br.com.levpatrim.view.home.user

import androidx.lifecycle.ViewModel
import br.com.levpatrim.model.repositories.UserRepository
import br.com.levpatrim.util.lazyDeferred

class ScreenUserViewModel(repository: UserRepository) : ViewModel() {

    val user by lazyDeferred {
        repository.getUser()
    }
}
