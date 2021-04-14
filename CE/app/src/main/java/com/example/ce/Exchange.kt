package com.example.ce

class Exchange(var c1: Currency, var c2: Currency) {
    fun convert_currency(amount: Double): Double {
        // Convert from c1 to c2
        // Step 1. c1 to usd
        val c1_usd = c1.rate * amount
        // Step 2. c1_usd to c2
        val output = c1_usd / c2.rate
        return output
    }
}