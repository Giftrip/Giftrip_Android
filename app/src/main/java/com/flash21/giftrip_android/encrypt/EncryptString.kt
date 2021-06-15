package com.flash21.giftrip_android.encrypt

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class EncryptString {
    fun hashSHA256(msg: String): String? {
        var messageDigest: MessageDigest? = null
        var hash: String? = null
        try {
            messageDigest = MessageDigest.getInstance("MD5")
            messageDigest.update(msg.toByteArray(), 0, msg.length)
            hash = BigInteger(1, messageDigest.digest()).toString(16)
        } catch (e: NoSuchAlgorithmException) {
            Log.e("Hashing Error", e.toString())
        }

        return hash
    }
}
