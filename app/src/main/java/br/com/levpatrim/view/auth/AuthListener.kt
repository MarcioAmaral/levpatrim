package br.com.levpatrim.view.auth

import br.com.levpatrim.model.db.entities.User

interface AuthListener {
    fun onStared()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}