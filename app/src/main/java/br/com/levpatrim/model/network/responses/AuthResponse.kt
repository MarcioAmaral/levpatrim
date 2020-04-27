package br.com.levpatrim.model.network.responses

import br.com.levpatrim.model.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)