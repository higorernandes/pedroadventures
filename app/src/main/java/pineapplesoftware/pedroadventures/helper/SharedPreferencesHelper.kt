package pineapplesoftware.pedroadventures.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by Higor Ernandes on 2017-07-26.
 */
class SharedPreferencesHelper
{
    companion object {

        val CHECKPOINT : String = "CHECKPOINT"

        fun saveStringInSharedPreferences(context: Context, key: String, value: String) {
            val sharedPreferences : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val sharedPreferencesEditor : SharedPreferences.Editor = sharedPreferences.edit();
            sharedPreferencesEditor.putString(key, value)
            sharedPreferencesEditor.commit()
        }

        fun getStringFromSharedPreferences(context: Context, key: String) : String? {
            val sharedPreferences : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return sharedPreferences.getString(key, null)
        }

        fun removeFromSharedPreferences(context: Context, key: String) {
            val sharedPreferences : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            if (sharedPreferences.contains(key)) {
                val sharedPreferencesEditor : SharedPreferences.Editor = sharedPreferences.edit();
                sharedPreferencesEditor.remove(key)
                sharedPreferencesEditor.commit()
            }
        }
    }
}