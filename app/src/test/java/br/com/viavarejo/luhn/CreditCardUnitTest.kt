package br.com.viavarejo.luhn

import br.com.viavarejo.luhn.Luhn10.Companion.sum
import org.junit.Test
import org.junit.Assert.*

class CreditCardUnitTest {
    @Test
    fun test_detect_empty_creditcard() {
        val card = CreditCardStrategy.detectCreditCard("")
        assertNull(card)
    }

    @Test
    fun test_detect_invalid_string() {
        val card = CreditCardStrategy.detectCreditCard("a")
        assertNull(card)
    }

    @Test
    fun test_detect_valid_card_but_invalid_size() {
        val card = CreditCardStrategy.detectCreditCard("4")
        assertNotNull(card)
        assertFalse(card!!.isValid("4"))
    }

    @Test
    fun test_detect_visa_card_but_mastercard_code() {
        val expected = "Visa"
        val card = CreditCardStrategy.detectCreditCard("4")
        assertEquals(card?.name, expected)
        assertFalse(card!!.isValid("5105105105105100"))
    }

    // mastercard

    @Test
    fun test_detect_mastercard_valid_prefix_51() {
        val expected = "Mastercard"
        val card = CreditCardStrategy.detectCreditCard("5105105105105100")
        assertEquals(card?.name, expected)
        assertTrue(card!!.isValid("5105105105105100"))
    }

    @Test
    fun test_detect_mastercard_valid_prefix_52() {
        assert(true) { "TODO: mastercard prefix 52" }
    }

    @Test
    fun test_detect_mastercard_valid_prefix_53() {
        assert(true) { "TODO: mastercard prefix 53" }
    }

    @Test
    fun test_detect_mastercard_valid_prefix_54() {
        assert(true) { "TODO: mastercard prefix 54" }
    }

    @Test
    fun test_detect_mastercard_valid_prefix_55() {
        assert(true) { "TODO: mastercard prefix 55" }
    }

    @Test
    fun test_detect_mastercard_but_invalid_code() {
        val code = "55"
        val card = CreditCardStrategy.detectCreditCard(code)
        assertEquals(card?.name, "Mastercard")
        assertFalse(card!!.isValid(code))
    }

    // visa

    @Test
    fun test_detect_visa_but_invalid_code() {
        val code = "4"
        val card = CreditCardStrategy.detectCreditCard(code)
        assertEquals(card?.name, "Visa")
        assertFalse(card!!.isValid(code))
    }

    @Test
    fun test_detect_visa_prefix_valid_13_size() {
        val expected = "Visa"
        val card = CreditCardStrategy.detectCreditCard("4222222222222")
        assertEquals(card?.name, expected)
        assertTrue(card!!.isValid("4222222222222"))
    }

    @Test
    fun test_detect_visa_prefix_valid_16_size() {
        assert(true) { "TODO: valid visa size 16" }
    }

    @Test
    fun test_detect_visa_prefix_16_size_but_invalid_code() {
        val expected = "Visa"
        val card = CreditCardStrategy.detectCreditCard("4222222222222222")
        assertEquals(card?.name, expected)
        assertFalse(card!!.isValid("4222222222222222"))
    }

    // amex

    @Test
    fun test_detect_valid_amex_prefix_34_but_invalid_code() {
        val expected = "Amex"
        val card = CreditCardStrategy.detectCreditCard("34")
        assertEquals(card?.name, expected)
        assertFalse(card!!.isValid("34"))
    }

    @Test
    fun test_detect_valid_amex_prefix_37_but_invalid_code() {
        val expected = "Amex"
        val card = CreditCardStrategy.detectCreditCard("37")
        assertEquals(card?.name, expected)
        assertFalse(card!!.isValid("37"))
    }

    @Test
    fun test_detect_amex_valid_prefix_34() {
        assert(true) { "TODO: amex prefix 34" }
    }

    @Test
    fun test_detect_amex_valid_prefix_37() {
        val expected = "Amex"
        val card = CreditCardStrategy.detectCreditCard("378282246310005")
        assertEquals(card?.name, expected)
        assertTrue(card!!.isValid("378282246310005"))
    }

    // Diners

    @Test
    fun test_detect_valid_diners_prefix_30_but_invalid_code() {
        val expected = "Diners"
        val card = CreditCardStrategy.detectCreditCard("30")
        assertEquals(card?.name, expected)
        assertFalse(card!!.isValid("30"))
    }

    @Test
    fun test_detect_valid_diners_prefix_36_but_invalid_code() {
        val expected = "Diners"
        val card = CreditCardStrategy.detectCreditCard("36")
        assertEquals(card?.name, expected)
        assertFalse(card!!.isValid("36"))
    }

    @Test
    fun test_detect_valid_diners_prefix_38_but_invalid_code() {
        val expected = "Diners"
        val card = CreditCardStrategy.detectCreditCard("38")
        assertEquals(card?.name, expected)
        assertFalse(card!!.isValid("38"))
    }

    @Test
    fun test_detect_diners_valid_prefix_30() {
        assert(true) { "TODO: diners prefix 30" }
    }

    @Test
    fun test_detect_diners_valid_prefix_36() {
        assert(true) { "TODO: diners prefix 36" }
    }

    @Test
    fun test_detect_diners_valid_prefix_38() {
        val expected = "Diners"
        val card = CreditCardStrategy.detectCreditCard("38520000023237")!!
        assertEquals(card.name, expected)
        assertTrue(card.isValid("38520000023237"))
    }

    // Discover

    @Test
    fun test_detect_valid_discover_prefix_but_invalid_code() {
        val expected = "Discover"
        val card = CreditCardStrategy.detectCreditCard("6011")!!
        assertEquals(card.name, expected)
        assertFalse(card.isValid("6011"))
    }

    @Test
    fun test_detect_discover_valid_prefix() {
        assert(true) { "TODO: discover prefix" }
    }

    // enRoute

    @Test
    fun test_detect_valid_enRoute_prefix_2014_but_invalid_code() {
        val expected = "enRoute"
        val card = CreditCardStrategy.detectCreditCard("2014")!!
        assertEquals(card.name, expected)
        assertFalse(card.isValid("2014"))
    }

    @Test
    fun test_detect_valid_enRoute_prefix_2149_but_invalid_code() {
        val expected = "enRoute"
        val card = CreditCardStrategy.detectCreditCard("2149")!!
        assertEquals(card.name, expected)
        assertFalse(card.isValid("2149"))
    }

    @Test
    fun test_detect_enRoute_valid_prefix_2014() {
        assert(true) { "TODO: enRoute prefix 2014" }
    }

    @Test
    fun test_detect_enRoute_valid_prefix_2149() {
        assert(true) { "TODO: enRoute prefix 2149" }
    }

    // JCB

    @Test
    fun test_detect_jcb_prefix_3_but_invalid_code() {
        val expected = "JCB"
        val card = CreditCardStrategy.detectCreditCard("3")!!
        assertEquals(card.name, expected)
        assertFalse(card.isValid("3"))
    }

    @Test
    fun test_detect_jcb_prefix_2131_but_invalid_code() {
        val expected = "JCB"
        val card = CreditCardStrategy.detectCreditCard("2131")!!
        assertEquals(card.name, expected)
        assertFalse(card.isValid("2131"))
    }

    @Test
    fun test_detect_jcb_prefix_1800_but_invalid_code() {
        val expected = "JCB"
        val card = CreditCardStrategy.detectCreditCard("1800")!!
        assertEquals(card.name, expected)
        assertFalse(card.isValid("1800"))
    }

    @Test
    fun test_detect_jcb_valid_prefix_3() {
        assert(true) { "TODO: JCB prefix 3" }
    }

    @Test
    fun test_detect_jcb_valid_prefix_2131() {
        assert(true) { "TODO: JCB prefix 2131" }
    }

    @Test
    fun test_detect_jcb_valid_prefix_1800() {
        assert(true) { "TODO: JCB prefix 1800" }
    }
}