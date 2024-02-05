package com.buranchikov.samecrystalsgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.buranchikov.samecrystalsgame.GameViewModel
import com.buranchikov.samecrystalsgame.R
import com.buranchikov.samecrystalsgame.databinding.FragmentMenuViewBinding
import com.buranchikov.samecrystalsgame.utils.APP_ACTIVITY


class MenuViewFragment : Fragment() {
    private lateinit var binding: FragmentMenuViewBinding
    private lateinit var gameViewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuViewBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        gameViewModel = APP_ACTIVITY.gameViewModel
        binding.btnPlay.setOnClickListener {

            APP_ACTIVITY.navController.navigate(R.id.action_menuViewFragment_to_gameSceneFragment)
        }
        binding.tvHighCoins.text= gameViewModel.getCoins().toString()

    }

}