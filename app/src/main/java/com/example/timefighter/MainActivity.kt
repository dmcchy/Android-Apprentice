package com.example.timefighter

// Adding these 4 additional imports for support
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

/**
 * "Activity" - is the composition of every android app.
 */

// MainActivity is a subclass of AppCompatActivity().
class MainActivity : AppCompatActivity() {

    internal var gameStarted = false

    internal lateinit var countDownTimer: CountDownTimer
    internal var initialCountDown: Long = 60000
    internal var countDownInterval: Long = 1000
    internal var timeLeft = 60

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

        resetGame()
    }

    private fun incrementScore() {

        if (!gameStarted){
            startGame()
        }

        score++

        // val newScore = "Your Score: " + Integer.toString(score)

        // We are now using our "strings.xml" to source our string.
        // This is so we can easily support multiple languages by just using that file and pointing to
        // global variables representing words in different languages.
        val newScore = getString(R.string.your_score, Integer.toString(score))
        gameScoreTextView.text = newScore
    }

    private fun resetGame() {
        // 1
        score = 0

        val initialScore = getString(R.string.your_score, Integer.toString(score))

        gameScoreTextView.text = initialScore

        var initialTimeLeft = getString(R.string.time_left, Integer.toString(60))
        timeLeftTextView.text = initialTimeLeft

        // 2
        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            // 3
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished.toInt() / 1000

                var timeLeftString = getString(R.string.time_left, Integer.toString(timeLeft))
                timeLeftTextView.text = timeLeftString
            }

            override fun onFinish() {
                // To be implemented later
                endGame()
            }
        }

        // 4
        gameStarted = false
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        // "Toast" is the equivalent of alert.
        Toast.makeText(this, getString(R.string.game_over_messsage,
            Integer.toString(score)), Toast.LENGTH_LONG).show()
        resetGame()
    }

}
