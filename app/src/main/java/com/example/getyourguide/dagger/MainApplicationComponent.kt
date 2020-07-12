package com.example.getyourguide.dagger

import com.example.getyourguide.MainApplication
import com.example.getyourguide.dagger.components.ActivityProviderModule
import com.example.getyourguide.dagger.network.APIModule
import com.example.getyourguide.dagger.network.RxJavaModule
import com.example.getyourguide.dagger.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, APIModule::class,
    ViewModelModule::class, ActivityProviderModule::class, RxJavaModule::class])
interface MainApplicationComponent: AndroidInjector<MainApplication>{
    @Component.Factory
    interface Factory: AndroidInjector.Factory<MainApplication>
}