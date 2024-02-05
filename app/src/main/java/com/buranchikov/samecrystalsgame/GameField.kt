package com.buranchikov.samecrystalsgame

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.buranchikov.samecrystalsgame.utils.TAG
import com.buranchikov.samecrystalsgame.utils.columnField
import com.buranchikov.samecrystalsgame.utils.rowField
import kotlin.random.Random
import androidx.gridlayout.widget.GridLayout
import com.buranchikov.samecrystalsgame.utils.showToast

class GameField(private val context: Context) {
    //массив будет повернут на 90гр.для перемешивания, поэтому сначала column, потом row.
    private val tempArray: Array<IntArray> = Array(columnField) { IntArray(rowField) }
    var logicMatrix: Array<IntArray> = Array(rowField) { IntArray(columnField) }

    val viewsArray: Array<Array<ImageView>> =
        Array(rowField) { Array(columnField) { ImageView(context) } }

    var countClicks: Int = 0
    var index_i1: Int = 0
    var index_i2: Int = 0
    var index_j1: Int = 0
    var index_j2: Int = 0

    var isStarting: Boolean = true


    //инициализация поля.
    //заполнить половину массива
    //скопировать эти значения во вторую половину, для образования парных чисел
    fun initArray() {
        //заполнение половины массива
        for (i in 0..<tempArray.size / 2) {
            for (j in 0..<tempArray[i].size) {
                tempArray[i][j] = Random.nextInt(1, 9)
            }
        }
//        заполнение второй половины массива
        for (i in tempArray.size / 2..<tempArray.size) {
            for (j in 0..<tempArray[i].size) {
                tempArray[i][j] = tempArray[i - 2][j]
            }
        }
        //перемешивание внтури строк, строки между собой
        for (i in tempArray) {
            i.shuffle()
        }
        tempArray.shuffle()

        rotateMatrix(tempArray)
    }


    fun setViewsArray() {
        for (i in 0..<rowField) {
            for (j in 0..<columnField) {
                val layoutParams = GridLayout.LayoutParams()
                layoutParams.apply {
                    width = context.resources.getDimension(R.dimen.size_cells).toInt()
                    height = context.resources.getDimension(R.dimen.size_cells).toInt()
                    setMargins(6, 6, 6, 6)
                }
                viewsArray[i][j].layoutParams = layoutParams
                viewsArray[i][j].setBackgroundResource(R.drawable.cell_back)
                viewsArray[i][j].setPadding(6, 6, 6, 6)
//                viewsArray[i][j].text = logicMatrix[i][j].toString()
                setImageToCells(logicMatrix[i][j], i, j)
            }
        }
    }


    private fun setImageToCells(value: Int, i: Int, j: Int) {
        when (value) {
            1 -> viewsArray[i][j].setImageResource(R.mipmap.crys1)
            2 -> viewsArray[i][j].setImageResource(R.mipmap.crys2)
            3 -> viewsArray[i][j].setImageResource(R.mipmap.crys3)
            4 -> viewsArray[i][j].setImageResource(R.mipmap.crys4)
            5 -> viewsArray[i][j].setImageResource(R.mipmap.crys5)
            6 -> viewsArray[i][j].setImageResource(R.mipmap.crys6)
            7 -> viewsArray[i][j].setImageResource(R.mipmap.crys7)
            8 -> viewsArray[i][j].setImageResource(R.mipmap.crys8)
            0 -> viewsArray[i][j].setImageResource(0)
        }


    }

    fun rotateMatrix(array: Array<IntArray>): Array<IntArray> {
        val rows = array.size
        val cols = array[0].size

        // rotating matrix

        logicMatrix = Array(cols) { IntArray(rows) }

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                // Переворачиваем строки и столбцы
                logicMatrix[j][rows - 1 - i] = array[i][j]
            }
        }

        return logicMatrix
    }

    fun size2d(array: Array<IntArray>) {

        Log.d(TAG, "size2d: ${array.size}x${array[0].size}")
    }

    fun compareCells(i1: Int, j1: Int, i2: Int, j2: Int) {
        countClicks = 0
        if (logicMatrix[i1][j1] == logicMatrix[i2][j2]) {
            logicMatrix[i1][j1] = 0
            logicMatrix[i2][j2] = 0
        }
        setViewsArray()

    }

    fun isGameOver(): Boolean {
        var controlSum = 0
        logicMatrix.forEach { it.forEach { controlSum += it } }
        return controlSum == 0

    }

    fun gameOver(isFinished: Boolean) {
        if (isFinished) {
//            showToast("FINISH")
            isStarting = false
        }
    }

}