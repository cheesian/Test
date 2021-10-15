package com.buupass.test.di

import com.buupass.test.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(activity: MainActivity)

}