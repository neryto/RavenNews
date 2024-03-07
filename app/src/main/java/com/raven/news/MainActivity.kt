package com.raven.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.raven.core.CommunicationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val viewModel: CommunicationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onStart() {
        super.onStart()
        setupCollectors()
    }

    private fun setupCollectors() {
        lifecycleScope.launch {
            viewModel.apply {
                navigateToDetail.collect { id ->
                    id?.let { notNullId ->
                        goToDetail(notNullId)
                        resetFlowStates()
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun goToDetail(id: String) {
        val bundle = Bundle().apply {
            putString("id",id)
        }
        navController.navigate(
            R.id.action_home_fragment_to_detailFragment,
            bundle
        )
    }
}
