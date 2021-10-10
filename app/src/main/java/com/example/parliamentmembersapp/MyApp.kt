package com.example.parliamentmembersapp

/**
 * MyApp define the application context
 * by An Huynh
 * on 18.9.2021
 */
import android.app.Application
import android.content.Context

class MyApp: Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}