package com.samit.kogantest.di.component

import android.app.Application
import com.samit.kogantest.KoganApp
import com.samit.kogantest.di.builder.ActivityBuilder
import com.samit.kogantest.di.module.AppModule
import com.samit.kogantest.di.module.ContextModule
import com.samit.kogantest.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ContextModule::class,
        ActivityBuilder::class]
)
interface KoganAppComponent : AndroidInjector<KoganApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): KoganAppComponent
    }
}