package com.vinarah.daggerkotlin.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.vinarah.daggerkotlin.viewmodel.MainViewModel
import com.vinarah.daggerkotlin.viewmodel.ViewModelFactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Vincent
 * *
 * @since 2017/10/03
 */
@Module abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindsFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
