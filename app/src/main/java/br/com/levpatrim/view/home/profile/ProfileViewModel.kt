package br.com.levpatrim.view.home.profile

import androidx.lifecycle.ViewModel
import br.com.levpatrim.model.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()
}
