package pineapplesoftware.pedroadventures.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_clue.*
import pineapplesoftware.pedroadventures.R
import pineapplesoftware.pedroadventures.manager.ClueManager

/**
 * Created by Higor Ernandes on 2017-09-24.
 */
class ClueActivity : AppCompatActivity(), TextWatcher
{
    private var mClueIndex : Int = 0

    companion object {
        private val CLUE_INDEX : String = "CLUE_INDEX"

        fun getActivityIntent(context: Context, clueIndex: Int) : Intent {
            val intent = Intent(context, ClueActivity::class.java)
            intent.putExtra(CLUE_INDEX, clueIndex)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_clue)

        mClueIndex = intent.getIntExtra(CLUE_INDEX, 0)

        prepareViews()
    }

    override fun afterTextChanged(p0: Editable?) { }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        clueErrorTextView.visibility = View.GONE
    }

    private fun prepareViews() {
        clueNextButton.setOnClickListener {
            if (isAnswerCorrect()) {
                if (mClueIndex == 9) { // If got to the end of the game, redirects to the end screen.
                    startActivity(EndActivity.getActivityIntent(this))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out)
                    finish()
                } else {
                    startActivity(PrizeActivity.getActivityIntent(this, mClueIndex))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out)
                    finish()
                }
            } else {
                clueErrorTextView.visibility = View.VISIBLE
            }
        }

        clueRootView.background = ClueManager.getInstance(this).getClueBackground(mClueIndex)
        clueTitleTextView.text = resources.getString(R.string.clue_title).replace("{number}", (mClueIndex + 1).toString())
        clueDescriptionTextView.text = ClueManager.getInstance(this).getClue(mClueIndex)
        clueQuestionTextView.text = ClueManager.getInstance(this).getClueQuestion(mClueIndex)

        clueAnswerEditText.addTextChangedListener(this)
    }

    private fun isAnswerCorrect() : Boolean {
        val clueAnswer = ClueManager.getInstance(this).getClueAnswer(mClueIndex)
        return clueAnswerEditText.text.toString().toLowerCase().trimStart().trimEnd() == clueAnswer.toLowerCase()
    }
}
