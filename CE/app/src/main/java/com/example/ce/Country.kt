package com.example.ce

data class Country (val image:Int, val name:String, val abbre:String)

object Countries {
    private val images = intArrayOf(
        R.drawable.thailand,
        R.drawable.brunei,
        R.drawable.cambodia,
        R.drawable.indonesia,
        R.drawable.laos,
        R.drawable.malaysia,
        R.drawable.myanma,
        R.drawable.philippines,
        R.drawable.vietnam,
        R.drawable.singpore,
        R.drawable.japan,
        R.drawable.china,
        R.drawable.uk,
        R.drawable.usa,
        R.drawable.eu,
    )

    private val countries = arrayOf(
        "THB (Thailand)",
        "BND (Brunei)",
        "KHR (Cambodia)",
        "IDR (Indonesia)",
        "LAK (Laos)",
        "MYR (Malaysia)",
        "MMK (Myanmar)",
        "PHP (Philippine)",
        "VND (Vietnam)",
        "SGD (Singapore)",
        "JPY (Japan)",
        "CNY (China)",
        "GBP (United Kingdom)",
        "USD (United State)",
        "EUR (European Union)",
    )

    private val abbreviation = arrayOf(
        "THB",
        "BND",
        "KHR",
        "IDR",
        "LAK",
        "MYR",
        "MMK",
        "PHP",
        "VND",
        "SGD",
        "JPY",
        "CNY",
        "GBP",
        "USD",
        "EUR",
    )

    var list: ArrayList<Country>? = null
        get() {

            if (field != null)
                return field

            field = ArrayList()
            for (i in images.indices) {

                val imageId = images[i]
                val countryName = countries[i]
                val abbreName = abbreviation[i]

                val country = Country(imageId, countryName, abbreName)
                field!!.add(country)
            }

            return field
        }
}