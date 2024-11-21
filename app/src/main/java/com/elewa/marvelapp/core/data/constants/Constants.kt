package com.elewa.marvelapp.core.data.constants

import com.elewa.marvelapp.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object Constants {

    val ts = Timestamp(System.currentTimeMillis()).time.toString()
    const val PAGE_ITEMS_COUNT = 10
    fun generateMd5Hash(): String{
        val input = "$ts${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray()))
            .toString(16)
            .padStart(32, '0')
    }
}