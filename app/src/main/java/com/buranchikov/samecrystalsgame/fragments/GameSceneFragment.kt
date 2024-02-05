package com.buranchikov.samecrystalsgame.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.buranchikov.samecrystalsgame.GameField
import com.buranchikov.samecrystalsgame.GameViewModel
import com.buranchikov.samecrystalsgame.R
import com.buranchikov.samecrystalsgame.databinding.FragmentGameSceneBinding
import com.buranchikov.samecrystalsgame.utils.APP_ACTIVITY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameSceneFragment() : Fragment() {

    lateinit var job: Job
    lateinit var binding: FragmentGameSceneBinding
    lateinit var gameField: GameField
    lateinit var gameViewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameSceneBinding.inflate(inflater, container, false)
        gameField = GameField(requireContext())
        startGameProcess()
        binding.tvTimer.text = "00:00"
        binding.tvCurentCoins.text = "100"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onStart() {
        super.onStart()

//            почему строки столбы работают наоборот?
        binding.containerGame.apply {
            columnCount = 4
            rowCount = 5
        }
//        binding.containerGame.layoutParams = layoutParams
        gameField.initArray()
        gameField.setViewsArray()
        game()
    }

    private fun game() {
        for (i in gameField.viewsArray.indices) {
            for (j in gameField.viewsArray[i].indices) {
                binding.containerGame.addView(gameField.viewsArray[i][j])

                gameField.viewsArray[i][j].setOnClickListener {
                    gameField.countClicks++

                    if (gameField.isStarting) {
                        gameViewModel.setStart(true)
                        startTimer()
                        gameField.isStarting = false
                    }

                    if (gameField.countClicks == 1) {
                        gameField.index_i1 = i
                        gameField.index_j1 = j
                    }

                    if (gameField.countClicks == 2 &&
                        gameField.viewsArray[i][j] != gameField.viewsArray[gameField.index_i1][gameField.index_j1]
                    ) {
                        gameField.index_i2 = i
                        gameField.index_j2 = j

                        gameField.compareCells(
                            i1 = gameField.index_i1, j1 = gameField.index_j1,
                            i2 = gameField.index_i2, j2 = gameField.index_j2
                        )
                        if (gameField.isGameOver()) {
                            gameField.gameOver(true)
                            gameViewModel.setStart(false)
                            MainScope().launch {
                                delay(1000)
                                val bundle = Bundle()
                                bundle.putString(
                                    "keyCoins", binding.tvCurentCoins.text.toString()
                                )
                                APP_ACTIVITY.navController.navigate(
                                    R.id.action_gameSceneFragment_to_endGameFragment
                                )
                            }
                        }
                    } else if (gameField.countClicks == 2 &&
                        gameField.viewsArray[i][j] == gameField.viewsArray[gameField.index_i1][gameField.index_j1]
                    ) {
                        gameField.countClicks = 0
                    }
                }
            }

        }
    }

    private fun startGameProcess() {

        gameViewModel = APP_ACTIVITY.gameViewModel
        //        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        gameViewModel.getLiveDataTime().observe(APP_ACTIVITY) { binding.tvTimer.text = it }
        gameViewModel.getLiveDataCoins().observe(APP_ACTIVITY) { binding.tvCurentCoins.text = it }
        gameViewModel.setTime(0)
        gameViewModel.setCoins(100)
    }

    fun startTimer() {
        CoroutineScope(Dispatchers.IO).launch { gameViewModel.startGame() }
    }
}