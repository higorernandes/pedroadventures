package pineapplesoftware.pedroadventures.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import pineapplesoftware.pedroadventures.R

/**
 * Created by Higor Ernandes on 2017-09-24.
 */
class MainActivity : AppCompatActivity()
{
    companion object {
        fun getActivityIntent(context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)

        prepareVideo()
        prepareViews()
    }

    private fun prepareViews() {
        mainStartButton.setOnClickListener {
            startActivity(ClueActivity.getActivityIntent(this, 0))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out)
            mainBackgroundVideoView.stopPlayback()
            finish()
        }
    }

    private fun prepareVideo() {
        try {
            mainBackgroundVideoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.start_video))
        } catch (e: Exception) {
//            Log.e("Error", e.message)
            e.printStackTrace()
        }

        mainBackgroundVideoView.requestFocus()
        mainBackgroundVideoView.setOnPreparedListener({ mediaPlayer ->
            mediaPlayer.isLooping = true
            mediaPlayer.setVolume(0f, 0f)
            mainBackgroundVideoView.start()
        })
    }
}
