package com.flash21.giftrip_android.encrypt

import java.math.BigInteger
import java.security.MessageDigest

class EncryptString {
    fun hashSHA512(msg: String): String? {
        val md = MessageDigest.getInstance("SHA-512")
        md.update(msg.toByteArray())
        val hex = java.lang.String.format("%0128x", BigInteger(1,md.digest()))

        return hex
    }
}
