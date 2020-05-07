package br.com.levpatrim.view.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import br.com.levpatrim.R
import br.com.levpatrim.databinding.ActivityLoginBinding
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

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)

    //   val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
    //   val api = MyApi(networkConnectionInterceptor)

    //   val db = AppDatabase(this)
    //   val repository = UserRepository(api, db)
    //   val factory = AuthViewModelFactory(repository)

       val binding: ActivityLoginBinding =
           DataBindingUtil.setContentView(this, R.layout.activity_login)

       val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
       binding.viewmodel = viewModel

       viewModel.authListener = this
   }

   override fun onStared() {
       progress_bar.show()
   }

   override fun onSuccess(user: User) {
       progress_bar.hide()
       Intent(this, HomeActivity::class.java).also {
           it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
           startActivity(it)
       }
   }

   override fun onFailure(message: String) {
       progress_bar.hide()
       root_layout.snackbar(message)
   }

}
