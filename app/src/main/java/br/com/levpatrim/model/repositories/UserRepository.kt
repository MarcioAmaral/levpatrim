package br.com.levpatrim.model.repositories

import br.com.levpatrim.model.db.AppDatabase
import br.com.levpatrim.model.db.entities.User
import br.com.levpatrim.model.network.MyApi
import br.com.levpatrim.model.network.SafeApiRequest
import br.com.levpatrim.model.network.responses.AuthResponse
import javax.inject.Inject
import javax.inject.Singleton

class UserRepository (
    val api: MyApi,
    val db: AppDatabase) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    suspend fun userSignup(
        name: String,
        email: String,
        password: String
    ) : AuthResponse {
        return apiRequest{api.userSignup(name, email, password)}
    }

    fun getUser() = db.getUserDao().getUser()
}