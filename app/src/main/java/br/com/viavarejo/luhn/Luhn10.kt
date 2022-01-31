package br.com.viavarejo.luhn

import java.lang.StringBuilder
import kotlin.text.*

class Luhn10 {

    companion object {
        inline fun isValid(value: String): Boolean {
            val sumNumber = Luhn10.Companion.sum(value)
            return sumNumber != null && sumNumber % 10 == 0
        }

        fun Companion.sum(value: String): Int? {
            fun sumEachDigit(number: Int): Int {
                var sum = 0
                number.toString().forEach { sum += it.toString().toInt() }
                return sum
            }
            if (value.isEmpty()) {
                return null
            }
            var sum = 0
            var isOdd = true
            StringBuilder(value).reverse().forEach {
                val number = it.toString().toIntOrNull() ?: return null
                sum += when (isOdd) {
                    true -> number
                    false -> sumEachDigit(number * 2)
                }
                isOdd = !isOdd
            }
            return sum
        }
    }
}