package com.buranchikov.samecrystalsgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.buranchikov.samecrystalsgame.GameViewModel
import com.buranchikov.samecrystalsgame.R
import com.buranchikov.samecrystalsgame.databinding.FragmentEndGameBinding
import com.buranchikov.samecrystalsgame.utils.APP_ACTIVITY

class EndGameFragment : Fragment() {

    private lateinit var binding: FragmentEndGameBinding
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEndGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        gameViewModel = APP_ACTIVITY.gameViewModel
        binding.tvResultCoins.text = gameViewModel.getCoins().toString()
        binding.ivHome.setOnClickListener {
            findNavController().navigate(R.id.action_endGameFragment_to_menuViewFragment)
        }
    }

}