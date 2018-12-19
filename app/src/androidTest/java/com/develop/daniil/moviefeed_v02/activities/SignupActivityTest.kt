package com.develop.daniil.moviefeed_v02.activities

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.runner.AndroidJUnit4
import com.develop.daniil.moviefeed_v02.RequestsClasses.Server
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SignupActivityTest{

    @Test
    @Throws(Exception::class)
    fun testRegTrue() {
        val testContext = getInstrumentation().getTargetContext()
        val server = Server(testContext)
        val reg = server.register("sFBF83DEGH8tNqtWXy0HJw==\n","sFBF83DEGH8tNqtWXy0HJw==\n","sFBF83DEGH8tNqtWXy0HJw==\n")
        assertEquals(true, reg.toString().trim().toInt() == 0)
    }

    @Test
    @Throws(Exception::class)
    fun testRegFalse() {
        val testContext = getInstrumentation().getTargetContext()
        val server = Server(testContext)
        val reg = server.register("sFBF83DEGH8tNqtWXy0HJw==\n","sFBF83DEGH8tNqtWXy0HJw==\n","sFBF83DEGH8tNqtWXy0HJw==\n")
        assertEquals(false, reg.toString().trim().toInt() == 0)
    }
}