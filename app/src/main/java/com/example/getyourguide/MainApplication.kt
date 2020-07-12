package com.example.getyourguide

import com.example.getyourguide.dagger.DaggerMainApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MainApplication> {
        return DaggerMainApplicationComponent.factory().create(this)
    }

}