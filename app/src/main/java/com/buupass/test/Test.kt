package com.buupass.test

import android.app.Application
import com.buupass.test.di.ApplicationComponent
import com.buupass.test.di.DaggerApplicationComponent

class Test: Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().build()
    }
}