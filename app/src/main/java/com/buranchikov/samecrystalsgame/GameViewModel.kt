package com.buranchikov.samecrystalsgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class GameViewModel : ViewModel() {
    private val liveDataTime = MutableLiveData<String>()
    private val liveDataCoins = MutableLiveData<String>()

    private var coins: Int = 100

    private var isStart = false
    private var min: Long = 0
    private var sec: Long = 0
    private var ms: Long = 0
//    private var tmpTime: Long = 0
    private var time: Long = 0

    fun getLiveDataTime(): MutableLiveData<String> {
        return liveDataTime
    }

    fun getLiveDataCoins(): MutableLiveData<String> {
        return liveDataCoins
    }

    fun isStart(): Boolean {
        return isStart
    }

    fun setStart(start: Boolean) {
        isStart = start
    }

    fun getCoins(): Int {
        return coins
    }

    fun setCoins(coins: Int) {
        this.coins = coins
    }

    fun setTime(time: Long) {
        this.time = time
    }
    fun getTime():Long {
        return time
    }

    suspend fun startGame() {
        while (isStart) {
            time++
            if (time > 20 && coins > 10) coins -= 5

            var stringTime = createStringTimer(time)

            liveDataTime.postValue(stringTime)
            liveDataCoins.postValue(coins.toString())

            delay(1000)
        }
    }

    private fun createStringTimer(time: Long): String {
        min = time / 60
        sec = time% 60
        return String.format("%02d:%02d", min, sec)
    }


}