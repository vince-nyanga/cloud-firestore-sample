package com.vinarah.daggerkotlin

import android.app.Activity
import android.app.Application
import com.vinarah.daggerkotlin.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * @author Vincent
 * @since 2017/10/03
 */
class AndroidApplication: Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun onCreate() {
        super.onCreate()
        AppInjector.inject(this)
    }
}