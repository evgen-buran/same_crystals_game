package com.buranchikov.samecrystalsgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.buranchikov.samecrystalsgame.databinding.ActivityMainBinding
import com.buranchikov.samecrystalsgame.utils.APP_ACTIVITY

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var gameViewModel: GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        gameViewModel = ViewModelProvider(APP_ACTIVITY).get(GameViewModel::class.java)
        gameViewModel.setCoins(0)
        navController = Navigation.findNavController(APP_ACTIVITY, R.id.nav_host_fragment)

    }
}