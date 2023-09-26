package com.example.kotlin_pr1.Data.Dagger

import com.example.kotlin_pr1.UI.View.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(fragment: MainFragment)
}