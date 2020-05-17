package com.salesianostriana.damkeepapp.common

import android.content.Context
import android.content.SharedPreferences
import com.salesianostriana.damkeepapp.di.MyApp


class SharedPreferencesManager private constructor(ctx: Context) {
    private val ctx: Context? = null

    companion object {
        private const val APP_SETTINGS_FILE = "APP_SETTINGS"
        private val sharedPreferences: SharedPreferences
            private get() = MyApp.instance.getSharedPreferences(
                APP_SETTINGS_FILE,
                Context.MODE_PRIVATE
            )

        fun setStringValue(dataLabel: String?, dataValue: String?) {
            val editor =
                sharedPreferences.edit()
            editor.putString(dataLabel, dataValue)
            editor.commit()
        }

        fun getStringValue(dataLabel: String?): String? {
            return sharedPreferences
                .getString(dataLabel, null)
        }

        fun removeStringValue(dataLabel: String?) {
            val editor =
                sharedPreferences.edit()
            editor.remove(dataLabel)
            editor.commit()
        }
    }
}