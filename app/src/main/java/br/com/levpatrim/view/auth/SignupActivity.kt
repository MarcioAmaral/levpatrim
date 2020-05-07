package br.com.levpatrim.view.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.levpatrim.R
import br.com.levpatrim.databinding.ActivitySignupBinding
import br.com.levpatrim.model.db.entities.User
import br.com.levpatrim.util.hide
import br.com.levpatrim.util.show
import br.com.levpatrim.util.snackbar
import br.com.levpatrim.view.home.HomeActivity
import br.com.levpatrim.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
Log.d("FOLLOWING","Antes 01 - ActivitySignupBinding")
        val binding: ActivitySignupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_signup)
        Log.d("FOLLOWING","Antes 02 - ViewModelProviders.of")
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        Log.d("FOLLOWING","Antes 03 - binding.viewmodel")
        binding.viewmodel = viewModel
        Log.d("FOLLOWING","Antes 04 - viewModel.authListener")
        viewModel.authListener = this

     /*   viewModel.getLoggedInUser().observe(this, Observer { user ->
              if (user != null) {
                  Intent(this, HomeActivity::class.java).also {
                      it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                      startActivity(it)
                  }
              }
          }) */
    }

    override fun onStared() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        Log.d("FOLLOWING","Antes 09 - Intent(this")
        Intent(this, HomeActivity::class.java).also {
            Log.d("FOLLOWING","Depois 10 - Intent(this")
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            Log.d("FOLLOWING","Depois 11 - it.flags")
            startActivity(it)
        }
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}
