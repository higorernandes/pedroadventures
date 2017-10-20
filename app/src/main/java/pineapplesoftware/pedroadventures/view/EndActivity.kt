package pineapplesoftware.pedroadventures.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_end.*
import pineapplesoftware.pedroadventures.R
import pineapplesoftware.pedroadventures.helper.SharedPreferencesHelper
import pineapplesoftware.pedroadventures.manager.ClueManager

/**
 * Created by Higor Ernandes on 2017-09-24.
 */
class EndActivity : AppCompatActivity()
{
    private var mPrizeIndex : Int = 9

    companion object {
        fun getActivityIntent(context: Context) : Intent {
            return Intent(context, EndActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end)

        SharedPreferencesHelper.removeFromSharedPreferences(this, SharedPreferencesHelper.CHECKPOINT)

        prepareViews()
    }

    private fun prepareViews() {
        val prizeDto = ClueManager.getInstance(this).getPrizeForClueIndex(9)
        endTitleTextView.text = prizeDto.congratulation
        endNameTextView.text = prizeDto.name
        endImageView.setImageDrawable(prizeDto.image)
        endDescriptionTextView.text = prizeDto.description

        endRootView.background = ClueManager.getInstance(this).getPrizeBackground(mPrizeIndex)

        endRestartButton.setOnClickListener {
            startActivity(MainActivity.getActivityIntent(this))
            finish()
        }
    }
}
