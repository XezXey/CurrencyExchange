package com.example.ce

public class Currency(var rate: Double, var abbreviation: String) {

    fun get_rate() : Double{
        return this.rate
    }

    fun get_abbre() : String{
        return this.abbreviation
    }
}

