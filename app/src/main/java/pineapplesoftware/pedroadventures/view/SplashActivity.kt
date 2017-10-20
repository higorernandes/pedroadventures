package pineapplesoftware.pedroadventures.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import pineapplesoftware.pedroadventures.R
import pineapplesoftware.pedroadventures.helper.SharedPreferencesHelper
import java.util.*

/**
 * Created by Higor Ernandes on 2017-09-24.
 */
class SplashActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                navigateToProperScreen()
            }
        }, 5000)
    }

    fun navigateToProperScreen() {
        val step = SharedPreferencesHelper.getStringFromSharedPreferences(this, SharedPreferencesHelper.CHECKPOINT)
        if ((step != null && !step.isEmpty()) && step != "10") {
            startActivity(ClueActivity.getActivityIntent(this, step.toInt()))
            finish()
        } else {
            SharedPreferencesHelper.removeFromSharedPreferences(this, SharedPreferencesHelper.CHECKPOINT)
            startActivity(MainActivity.getActivityIntent(this))
            finish()
        }
    }
}
