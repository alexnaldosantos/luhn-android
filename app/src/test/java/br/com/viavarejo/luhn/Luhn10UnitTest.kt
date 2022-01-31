package br.com.viavarejo.luhn

import br.com.viavarejo.luhn.Luhn10.Companion.sum
import org.junit.Test
import org.junit.Assert.*

class Luhn10UnitTest {

    @Test
    fun test_luhn_sum_when_empty_value() {
        val value = ""
        val sum = Luhn10.Companion.sum(value)
        assertNull(sum)
    }

    @Test
    fun test_luhn_invalid_when_empty_value() {
        val value = ""
        assertFalse(Luhn10.isValid(value))
    }

    @Test
    fun test_luhn_sum_when_invalid_value() {
        val value = "a"
        val sum = Luhn10.Companion.sum(value)
        assertNull(sum)
    }

    @Test
    fun test_luhn_invalid_when_invalid_value() {
        val value = "a"
        assertFalse(Luhn10.isValid(value))
    }

    @Test
    fun test_luhn_sum_when_correct_value() {
        val expected = 70
        val value = "49927398716"
        val sum = Luhn10.Companion.sum(value)
        assertEquals(sum, expected)
    }

    @Test
    fun test_luhn_valid_when_correct_value() {
        val value = "49927398716"
        assertTrue(Luhn10.isValid(value))
    }
}