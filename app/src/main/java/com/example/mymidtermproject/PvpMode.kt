package com.example.mymidtermproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.mymidtermproject.databinding.ActivityPvpModeBinding

class PvpMode : AppCompatActivity() {
    private var firstTurn = "X"
    private var currentTurn = "X"
    private var pointsOfCrosses = 0
    private var pointsOfNoughts = 0
    private var board = mutableListOf<Button>()

    private lateinit var binding : ActivityPvpModeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPvpModeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    //adding buttons to a list to interact with them later
    private fun initBoard() {
        board.add(binding.B1)
        board.add(binding.B2)
        board.add(binding.B3)
        board.add(binding.B4)
        board.add(binding.B5)
        board.add(binding.B6)
        board.add(binding.B7)
        board.add(binding.B8)
        board.add(binding.B9)
    }

    //function to check if the button texts are the same
    private fun match(button: Button, symbol : String): Boolean = button.text == symbol

    //function that checks for victory button combinations
    private fun checkForVictory(s: String): Boolean {
        //horizontal victory
        if(match(binding.B1,s) && match(binding.B2,s) && match(binding.B3,s))
            return true
        if(match(binding.B4,s) && match(binding.B5,s) && match(binding.B6,s))
            return true
        if(match(binding.B7,s) && match(binding.B8,s) && match(binding.B9,s))
            return true

        //vertical victory
        if(match(binding.B1,s) && match(binding.B4,s) && match(binding.B7,s))
            return true
        if(match(binding.B2,s) && match(binding.B5,s) && match(binding.B8,s))
            return true
        if(match(binding.B3,s) && match(binding.B6,s) && match(binding.B9,s))
            return true

        //diagonal victory
        if(match(binding.B1,s) && match(binding.B5,s) && match(binding.B9,s))
            return true
        if(match(binding.B3,s) && match(binding.B5,s) && match(binding.B7,s))
            return true

        return false
    }

    //function that resets the board and makes the next first turn X if it was O and vice versa
    private fun resetBoard() {
        for(button in board)
        {
            button.text = ""
        }

        if(firstTurn == "O")
            firstTurn = "X"
        else if(firstTurn == "X")
            firstTurn = "O"

        currentTurn = firstTurn
        setNextGameFirstTurn()
    }

    //functions that shows the game result in a separate alert window
    private fun gameResult(title: String) {
        AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
            .setTitle(title)
            .setMessage("\nScore of Noughts = $pointsOfNoughts\n\nScore of Crosses = $pointsOfCrosses")
            .setPositiveButton("Clear the board")
            { _,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    //function that check if the game board is full
    private fun fullBoard(): Boolean {
        for(button in board)
        {
            if(button.text == "")
                return false
        }
        return true
    }

    //function that changes next game first turn to the opposite
    private fun setNextGameFirstTurn() {
        var turnText = ""
        if(currentTurn == "X")
            turnText = "Place X"
        else if(currentTurn == "O")
            turnText = "Place O"
        binding.playersTurn.text = turnText
    }

    //function that adds X or O text to the selected button
    private fun addToBoard(button: Button) {
        if(button.text != "")
            return

        if(currentTurn == "O")
        {
            button.text = "O"
            currentTurn = "X"
        }
        else if(currentTurn == "X")
        {
            button.text = "X"
            currentTurn = "O"
        }
        setNextGameFirstTurn()
    }

    //function that makes the alert title
    private fun alertMessageTitle() {
        if(checkForVictory("O")) {
            pointsOfNoughts++
            gameResult("Congrats to Noughts' player!")
        }
        else if(checkForVictory("X")) {
            pointsOfCrosses++
            gameResult("Congrats to Crosses' player!")
        }

        if(fullBoard()) {
            gameResult("Draw!")
        }
    }
    fun buttonIsTapped(view: View) {
        if(view !is Button)
            return
        addToBoard(view)
        alertMessageTitle()
    }
}