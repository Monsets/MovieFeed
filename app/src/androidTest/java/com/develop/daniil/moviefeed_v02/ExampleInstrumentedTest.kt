package com.develop.daniil.moviefeed_v02

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.develop.daniil.moviefeed_v02.RequestsClasses.Server

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleInstrumentedTest {
    val appContext = InstrumentationRegistry.getTargetContext()
    @Test
    fun useAppContext() {
        // Context of the app under test.
        assertEquals("com.develop.daniil.moviefeed_v02", appContext.packageName)
        val s = Server(appContext)
        val resp = s.register("123456", "123", "1123")
        assertEquals("true", resp)
    }
}
