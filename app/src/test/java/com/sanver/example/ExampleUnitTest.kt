package com.sanver.example

import android.annotation.SuppressLint
import org.junit.Test

import org.junit.Assert.*
import java.util.regex.Pattern

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isAlgorithm() {

        assertEquals("abbcccaaeeeeb bfffffca ccab", questionTwo("abbcccaaeeeeb bfffffca ccab",9))
        assertEquals("abbcccaaeeeeb b*****ca ccab", questionTwo("abbcccaaeeeeb bfffffca ccab",5))
        assertEquals("abbcccaa****b b*****ca ccab", questionTwo("abbcccaaeeeeb bfffffca ccab",4))
        assertEquals("abb***aa****b b*****ca ccab", questionTwo("abbcccaaeeeeb bfffffca ccab",3))



    }


    private fun questionTwo(stringData: String, count: Int):String {
        var data = stringData
        val m = Pattern.compile("(.+)\\1{" + (count - 1) + ",}").matcher(data)
        while (m.find()) {
            data = data.replace(m.group(), m.group().replace("(.)".toRegex(), "*"))
        }
        //print(data)
    return(data)
    }
}