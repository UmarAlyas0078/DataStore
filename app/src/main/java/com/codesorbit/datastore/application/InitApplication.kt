package com.codesorbit.datastore.application

import android.app.Application
import androidx.datastore.preferences.preferencesKey

class InitApplication : Application() {


    companion object {
        val NIGHT_MODE = preferencesKey<Boolean>("NIGHT_MODE")
        var isNightMode: Boolean = false
    }

}