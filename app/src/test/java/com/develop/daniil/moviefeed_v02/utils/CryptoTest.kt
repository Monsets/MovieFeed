package com.develop.daniil.moviefeed_v02.utils

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import android.util.Base64
import org.mockito.Mockito
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import javax.crypto.spec.SecretKeySpec


@RunWith(PowerMockRunner::class)
@PrepareForTest(Base64::class)
class CryptoTest{
    val crypto = Crypto

    fun <T> any(): T = Mockito.any<T>()

    @Before
    fun beforeTest() {
        PowerMockito.mockStatic(Base64::class.java)

        val stringKey= "mEg8brQLbDGkSMIqZt7TteXo1RGcrIMntlXItcSDZIk="
        val enckey = java.util.Base64.getDecoder().decode(stringKey.toByteArray())
        val kettostr = java.util.Base64.getEncoder().encodeToString(enckey)
        Mockito.`when`(crypto.stringToKey(any())).thenReturn(SecretKeySpec(enckey, 0, enckey.size, "AES"))

        val key = crypto.stringToKey("mEg8brQLbDGkSMIqZt7TteXo1RGcrIMntlXItcSDZIk=")

        Mockito.`when`(crypto.decryptString(any(),any())).thenAnswer { invocation ->
            java.util.Base64.getDecoder().decode((key.toString()))
        }
    }

    @Test
    @Throws(Exception::class)
    fun testEncryptString() {
        val key = crypto.stringToKey("mEg8brQLbDGkSMIqZt7TteXo1RGcrIMntlXItcSDZIk=")
        assertEquals("3C/AVpAq/2ImWQRF6r/BoA==", crypto.encryptString("testString",key))
    }

    @Test
    @Throws(Exception::class)
    fun testDecryptString() {
        val key = crypto.stringToKey("mEg8brQLbDGkSMIqZt7TteXo1RGcrIMntlXItcSDZIk=")
        assertEquals("testString", crypto.decryptString("3C/AVpAq/2ImWQRF6r/BoA==",key))
    }

    @Test
    @Throws(Exception::class)
    fun testStringToKey() {
        val key = crypto.stringToKey("mEg8brQLbDGkSMIqZt7TteXo1RGcrIMntlXItcSDZIk=")
        assertEquals(key, crypto.stringToKey("mEg8brQLbDGkSMIqZt7TteXo1RGcrIMntlXItcSDZIk="))
    }

    @Test
    @Throws(Exception::class)
    fun testKeytoString() {
        val key = crypto.stringToKey("mEg8brQLbDGkSMIqZt7TteXo1RGcrIMntlXItcSDZIk=")
        assertEquals("mEg8brQLbDGkSMIqZt7TteXo1RGcrIMntlXItcSDZIk=", crypto.keyToString(key))
    }

    @Test
    @Throws(Exception::class)
    fun testGetHash() {
        assertEquals("mEg8brQLbDGkSMIqZt7TteXo1RGcrIMntlXItcSDZIk=", crypto.getHash("testkey"))
    }
}