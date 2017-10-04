package com.vinarah.daggerkotlin.di

import com.vinarah.daggerkotlin.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Vincent
 * @since 2017/10/03
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}