package pineapplesoftware.pedroadventures.manager

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import pineapplesoftware.pedroadventures.model.PrizeDto
import pineapplesoftware.pedroadventures.R

@SuppressLint("Registered")
/**
 * Created by Higor Ernandes on 2017-09-24.
 */
class ClueManager private constructor(context: Context)
{
    companion object {
        private var mInstance : ClueManager? = null

        private var mContext : Context? = null
        private var mClues : Array<String>? = null
        private var mClueQuestions : Array<String>? = null
        private var mClueAnswers : Array<String>? = null

        private var mPrizeCongratz : Array<String>? = null
        private var mPrizeNames: Array<String>? = null
        private var mPrizeDescriptions : Array<String>? = null

        fun getInstance(context: Context) : ClueManager {
            if (mInstance == null) {
                mInstance = ClueManager(context)
            }
            return mInstance as ClueManager
        }
    }

    init {
        mContext = context
        mClues = mContext?.resources?.getStringArray(R.array.clue_list)
        mClueQuestions = mContext?.resources?.getStringArray(R.array.question_list)
        mClueAnswers = mContext?.resources?.getStringArray(R.array.answer_list)

        mPrizeCongratz = mContext?.resources?.getStringArray(R.array.prize_congratz)
        mPrizeNames = mContext?.resources?.getStringArray(R.array.prize_names)
        mPrizeDescriptions = mContext?.resources?.getStringArray(R.array.prize_descriptions)
    }

    //region ClueActivity Methods

    fun getClue(index: Int) : String {
        if (mClues != null) {
            return mClues!![index]
        }
        return ""
    }

    fun getClueQuestion(index: Int) : String {
        if (mClueQuestions != null) {
            return mClueQuestions!![index]
        }
        return ""
    }

    fun getClueAnswer(index: Int) : String {
        if (mClueAnswers != null) {
            return mClueAnswers!![index]
        }
        return ""
    }

    //endregion

    //region PrizeActivity Methods

    private fun getDrawableForIndex(index: Int) : Drawable {
        val drawableName = "img_prize${index + 1}"
        return ContextCompat.getDrawable(mContext, mContext?.resources?.getIdentifier(drawableName, "drawable", mContext?.packageName)!!)
    }

    fun getPrizeForClueIndex(index: Int) : PrizeDto {
        val prizeDto = PrizeDto()

        prizeDto.clueNumber = index
        prizeDto.name = mPrizeNames?.get(index)
        prizeDto.image = getDrawableForIndex(index)
        prizeDto.congratulation = mPrizeCongratz?.get(index)
        prizeDto.description = mPrizeDescriptions?.get(index)

        return prizeDto
    }

    fun getClueBackground(index: Int) : Drawable {
        val drawableName = "img_clue_background${index + 1}"
        return ContextCompat.getDrawable(mContext, mContext?.resources?.getIdentifier(drawableName, "drawable", mContext?.packageName)!!)
    }

    fun getPrizeBackground(index: Int) : Drawable {
        val drawableName = "img_prize_background${index + 1}"
        return ContextCompat.getDrawable(mContext, mContext?.resources?.getIdentifier(drawableName, "drawable", mContext?.packageName)!!)
    }

    //endregion
}
