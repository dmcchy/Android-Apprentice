package com.example.timefighter

// Adding these 4 additional imports for support
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

/**
 * "Activity" - is the composition of every android app.
 */

// MainActivity is a subclass of AppCompatActivity().
class MainActivity : AppCompatActivity() {

    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView
    internal lateinit var tapMeButton: Button

    internal var score = 0

    // I think this is a lifecycle entry.
    override fun onCreate(savedInstanceState: Bundle?) {

        // This is required. Bootstraps your layout and passes it on the Android screen.
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        // Connect variables to views.

        // Variables
        gameScoreTextView = findViewById<TextView>(R.id.game_score_text_view)
        timeLeftTextView = findViewById<TextView>(R.id.time_left_text_view)
        tapMeButton = findViewById<Button>(R.id.tap_me_button)

        // Listener
        tapMeButton.setOnClickListener { _ -> incrementScore() }
    }

    private fun incrementScore() {
        score++

        // val newScore = "Your Score: " + Integer.toString(score)

        // We are now using our "strings.xml" to source our string.
        // This is so we can easily support multiple languages by just using that file and pointing to
        // global variables representing words in different languages.
        val newScore = getString(R.string.your_score, Integer.toString(score))
        gameScoreTextView.text = newScore
    }

    private fun resetGame() {

    }

    private fun startGame() {

    }

    private fun endGame() {

    }

}
