package com.example.getyourguide.dagger.components

import com.example.getyourguide.dagger.scope.FragmentScope
import com.example.getyourguide.ui.userrating.RatingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RatingFragmentModule{

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindFragment(): RatingFragment
}