package com.example.getyourguide.dagger.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.getyourguide.ui.userrating.viewmodel.UserRatingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserRatingsViewModel::class)
    internal abstract fun ratingFragmentViewModel(viewModel: UserRatingsViewModel): ViewModel

}