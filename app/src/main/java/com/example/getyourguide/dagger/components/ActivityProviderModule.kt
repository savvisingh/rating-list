package com.example.getyourguide.dagger.components

import com.example.getyourguide.dagger.scope.ActivityScope
import com.example.getyourguide.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityProviderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules =[RatingFragmentModule::class])
    abstract fun bindMainActivity(): MainActivity
}