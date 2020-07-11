package com.samit.kogantest

import com.facebook.stetho.BuildConfig
import com.facebook.stetho.Stetho
import com.samit.kogantest.di.component.DaggerKoganAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class KoganApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerKoganAppComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}