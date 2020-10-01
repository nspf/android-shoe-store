package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.fragments.ShoeListingFragment
import com.udacity.shoestore.fragments.WelcomeFragment
import com.udacity.shoestore.util.EventObserver
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ShoeStoreViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var host: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ShoeStoreViewModel::class.java)

        setUpBinding()
        setUpNavigation()
        setUpUi()
    }

    private fun setUpBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setUpNavigation() {
        host = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        navController = host.navController

        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.loginFragment)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setUpUi() {
        viewModel.newDestination.observe(this, EventObserver {
            navigate(it)
        })
    }

    private fun navigate(destId: Int) {
        navController.navigate(destId)
    }

    override fun onSupportNavigateUp(): Boolean {
        host.childFragmentManager.primaryNavigationFragment?.let { fragment ->
            if ((fragment is WelcomeFragment || fragment is ShoeListingFragment) && viewModel.isOnboarded()) {
                if (!navController.popBackStack()) finish()
            }
        }
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }
}
