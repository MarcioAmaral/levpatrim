package br.com.levpatrim.viewmodel

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import br.com.levpatrim.model.repositories.UserRepository
import br.com.levpatrim.util.ApiException
import br.com.levpatrim.util.Coroutines
import br.com.levpatrim.util.NoInternetException
import br.com.levpatrim.view.auth.AuthListener
import br.com.levpatrim.view.auth.LoginActivity
import br.com.levpatrim.view.auth.SignupActivity

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordconfirm: String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStared()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Inválido email ou senha")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }

    fun onSignupButtonClick(view: View) {
        Log.d("FOLLOWING","Antes 05 - authListener?.onStared()")
        authListener?.onStared()

        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Name is required")
            return
        }

        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Email is required")
            return
        }

        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Please enter a password")
            return
        }

        if (password != passwordconfirm) {
            authListener?.onFailure("Password did not match")
            return
        }
        Log.d("FOLLOWING","Antes 06 - Coroutines.main")
        Coroutines.main {
            try {
                val authResponse = repository.userSignup(name!!, email!!, password!!)
                Log.d("FOLLOWING","Antes 07 - authResponse.user?.let")
                authResponse.user?.let {
                    Log.d("FOLLOWING","Antes 08 - authListener?.onSuccess(it)")
                    authListener?.onSuccess(it)
                    Log.d("FOLLOWING","Antes 12 - Depois authListener?.onSuccess(it)")
                    Log.d("FOLLOWING","Antes 13 - Antes repository.saveUser(it)")
                    repository.saveUser(it)
                    Log.d("FOLLOWING","Antes 14 - Depois repository.saveUser(it)")
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }

    fun onLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignup(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

}