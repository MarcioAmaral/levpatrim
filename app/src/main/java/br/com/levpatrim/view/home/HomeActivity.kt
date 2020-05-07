package br.com.levpatrim.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import br.com.levpatrim.R
import br.com.levpatrim.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        setSupportActionBar(toolbar)
        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.navHostFragment)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)


        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, bundle: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
