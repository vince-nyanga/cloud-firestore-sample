package com.vinarah.daggerkotlin.di

import android.app.Application
import com.vinarah.daggerkotlin.AndroidApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

/**
 * @author Vincent
 * @since 2017/10/03
 */
@Component(modules = arrayOf(AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app: Application): Builder
        fun build():AppComponent
    }

    fun inject(app: AndroidApplication)
}