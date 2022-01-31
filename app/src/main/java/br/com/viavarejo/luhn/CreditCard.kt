package br.com.viavarejo.luhn

interface CreditCard {
    val name: String
    val prefix: Array<String>
    val size: Int
    fun isValid(cardCode: String) : Boolean
}

open class DefaultCreditCard(override val name: String,
                             override val prefix: Array<String>,
                             override val size: Int
) : CreditCard {

    override fun isValid(cardCode: String) : Boolean {
        if(!isIt(cardCode)){
            return false
        }
        return cardCode.length == size && Luhn10.isValid(cardCode)
    }

    internal fun isIt(cardCode: String): Boolean {
        val items = prefix.filter { checkMatchCreditCardPrefix(cardCode, it) }
        return items.isNotEmpty()
    }

    private fun checkMatchCreditCardPrefix(cardCode: String, prefix: String) : Boolean {
        return cardCode.isNotEmpty() && cardCode.length >= prefix.length && cardCode.substring(0,prefix.length) == prefix
    }
}

 class Visa : DefaultCreditCard("Visa", arrayOf("4"), 0) {

    override fun isValid(cardCode: String) : Boolean {
        if(!isIt(cardCode)){
            return false
        }
        return (cardCode.length == 13 || cardCode.length == 16) && Luhn10.isValid(cardCode)
    }
}

class CreditCardStrategy {

    companion object {
        /*
        A ordem da lista é importante porque alguns prefixos são aparentemente compostos por outros.
        Ex: "3" (JCB) é o mesmo inicial do Amex e Diners. Por isso, na lista, o JCB deve ser o último a ser verificado.
        * */
        private val creditCards = arrayOf(
            DefaultCreditCard("Mastercard", arrayOf("51", "52", "53", "54", "55"), 16),
            Visa(),
            DefaultCreditCard("Amex", arrayOf("34", "37"), 15),
            DefaultCreditCard("Diners", arrayOf("30", "36", "38"), 14),
            DefaultCreditCard("Discover", arrayOf("6011"), 16),
            DefaultCreditCard("enRoute", arrayOf("2014", "2149"), 15),
            DefaultCreditCard("JCB", arrayOf("3"), 16),
            DefaultCreditCard("JCB", arrayOf("2131", "1800"), 15)
        )

        fun detectCreditCard(cardCode: String): CreditCard? {
            for (creditCard in creditCards) {
                if (creditCard.isIt(cardCode)) {
                    return creditCard
                }
            }
            return null
        }
    }
}