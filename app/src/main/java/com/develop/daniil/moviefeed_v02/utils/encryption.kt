package com.develop.daniil.moviefeed_v02.utils
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

import android.util.Base64

object Crypto {

    /*
    * encryptString encrypted string by AES algorithm
    * @message - input string for encrypt
    * @secret - key for encryption
    * Return: encrypted string
    */
    @Throws(Exception::class)
    fun encryptString(message: String, secret: SecretKey): String {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, secret)
        return  Base64.encodeToString(cipher.doFinal(message.toByteArray(charset("UTF-8"))),Base64.DEFAULT)
    }

    /*
    * decryptString decrypted string by AES algorithm
    * @message - input string for decrypt
    * @secret - key for decryption
    * Return: decrypted string
    */
    @Throws(Exception::class)
    fun decryptString(cipherText: String, secret: SecretKey): String {
        val cipherTextByte = Base64.decode(cipherText, Base64.DEFAULT) as ByteArray
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, secret)
        return String(cipher.doFinal(cipherTextByte))
    }

    /*
    * keyToString convert SecretKey to key string
    * @secret - key for convert
    * Return: key converted to string
    */
    fun keyToString(secret: SecretKey): String {
        return Base64.encodeToString(secret.encoded, Base64.DEFAULT)
    }

    /*
    * stringToKey convert key string to SecretKey
    * @stringKey - string key for convert
    * Return: SecretKey converted from key string
    */
    fun stringToKey(stringKey: String): SecretKey {
        val encodedKey = Base64.decode(stringKey.trim { it <= ' ' }, Base64.DEFAULT)
        return SecretKeySpec(encodedKey, 0, encodedKey.size, "AES")
    }

    /*
    * getPassHash get's md5 password hash
    * @key - string key
    * Return: password hash in string
    */
    fun getPassHash(key: String): String {
        var digestsPass: ByteArray? = null
        try {
            val passByte = key.toByteArray(charset("UTF-8"))
            val md = MessageDigest.getInstance("MD5")
            digestsPass = md.digest(passByte)
            return keyToString(SecretKeySpec(digestsPass, 0, digestsPass.size, "AES"))
        } catch (e: Exception) {
            return error("Error")
        }
    }


}