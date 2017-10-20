package pineapplesoftware.pedroadventures.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_prize.*
import pineapplesoftware.pedroadventures.R
import pineapplesoftware.pedroadventures.helper.SharedPreferencesHelper
import pineapplesoftware.pedroadventures.manager.ClueManager

/**
 * Created by Higor Ernandes on 2017-09-24.
 */
class PrizeActivity : AppCompatActivity()
{
    private var mPrizeIndex : Int = 0

    companion object {
        private val PRIZE_INDEX : String = "PRIZE_INDEX"

        fun getActivityIntent(context: Context, prizeIndex: Int) : Intent {
            val intent = Intent(context, PrizeActivity::class.java)
            intent.putExtra(PRIZE_INDEX, prizeIndex)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_prize)

        mPrizeIndex = intent.getIntExtra(PRIZE_INDEX, 0)
        SharedPreferencesHelper.saveStringInSharedPreferences(this, SharedPreferencesHelper.CHECKPOINT, (mPrizeIndex + 1).toString())

        prepareViews()
    }

    private fun prepareViews() {
        val prizeDto = ClueManager.getInstance(this).getPrizeForClueIndex(mPrizeIndex)
        prizeTitleTextView.text = prizeDto.congratulation
        prizeNameTextView.text = prizeDto.name
        prizeImageView.setImageDrawable(prizeDto.image)
        prizeDescriptionTextView.text = prizeDto.description

        prizeRootView.background = ClueManager.getInstance(this).getPrizeBackground(mPrizeIndex)

        prizeNextButton.setOnClickListener {
            mPrizeIndex++
            startActivity(ClueActivity.getActivityIntent(this, mPrizeIndex))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out)
            finish()
        }
    }
}
