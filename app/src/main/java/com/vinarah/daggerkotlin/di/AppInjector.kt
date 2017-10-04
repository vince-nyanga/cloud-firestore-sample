package com.vinarah.daggerkotlin.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.vinarah.daggerkotlin.AndroidApplication
import dagger.android.AndroidInjection

/**
 * @author Vincent
 * @since 2017/10/03
 */
class AppInjector {
    companion object {
        fun inject(app: AndroidApplication){
            DaggerAppComponent.builder().application(app).build().inject(app)
            app.registerActivityLifecycleCallbacks(object: Application.ActivityLifecycleCallbacks{
                override fun onActivityPaused(p0: Activity?) {

                }

                override fun onActivityResumed(p0: Activity?) {

                }

                override fun onActivityStarted(p0: Activity?) {

                }

                override fun onActivityDestroyed(p0: Activity?) {

                }

                override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {

                }

                override fun onActivityStopped(p0: Activity?) {

                }

                override fun onActivityCreated(activity: Activity?, p1: Bundle?) {
                    handleActivity(activity)
                }

            })
        }

        private fun handleActivity(activity: Activity?) {
            if(activity is Injectable)
                AndroidInjection.inject(activity)
        }
    }
}