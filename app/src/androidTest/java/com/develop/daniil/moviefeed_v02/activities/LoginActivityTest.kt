package com.develop.daniil.moviefeed_v02.activities

import android.support.test.InstrumentationRegistry.getInstrumentation
import com.develop.daniil.moviefeed_v02.RequestsClasses.Server
import org.junit.Assert.*
import org.junit.Test

class LoginActivityTest{

    @Test
    @Throws(Exception::class)
    fun testAuthorizeTrue() {
        val server = Server(getInstrumentation().context)
        val auth = server.authorize("9W/94Vg/tO8cacQ9iYkLxg==\n", "dxgsCl7uLyzhNWOVgnQTng==\n")
        assertEquals(true, auth.toString().trim().toInt() == 0)
    }

    @Test
    @Throws(Exception::class)
    fun testAuthorizeFalse() {
        val server = Server(getInstrumentation().context)
        val auth = server.authorize("false", "false")
        assertEquals(false, auth.toString().trim().toInt() == 0)
    }

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