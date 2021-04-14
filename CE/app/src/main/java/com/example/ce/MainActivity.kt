package com.example.ce

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    public lateinit var thb: Currency
    public lateinit var bnd: Currency
    public lateinit var khr: Currency
    public lateinit var idr: Currency
    public lateinit var lak: Currency
    public lateinit var myr: Currency
    public lateinit var mmk: Currency
    public lateinit var php: Currency
    public lateinit var vnd: Currency
    public lateinit var sgd: Currency
    public lateinit var jpy: Currency
    public lateinit var cny: Currency
    public lateinit var gbp: Currency
    public lateinit var usd: Currency
    public lateinit var eur: Currency
    public lateinit var ce: Exchange
    public lateinit var currency_list: ArrayList<Currency>
    public var position_to = 0
    public var position_from = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCustomSpinner()
        initialize_currency()
        exchange_currency()
    }

    private fun exchange_currency(){
        // CurrencyExchange
        ce = Exchange(currency_list[position_from], currency_list[position_to])
        // Get reference to Button
        val exchange_button = findViewById(R.id.exchange) as Button
        exchange_button.setOnClickListener {
            // Text view to set
            val get_textview = findViewById<View>(R.id.get) as TextView
            // Read amount
            val amount_text = findViewById<View>(R.id.amount) as EditText
            val amount_value = amount_text.text.toString().toDouble()
            Log.d("Debug", "Amount : $amount_value")
            Log.d("Debug", "Convert : ${ce.convert_currency(amount_value)}")

            if ((amount_value).compareTo(0.0) == 0) {
                Toast.makeText(
                    this@MainActivity,
                    "Please insert an amount to exchange!!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                val converted = ce.convert_currency(amount_value)
                get_textview.text = "GET : %f %s".format(converted, currency_list[position_to].abbreviation)
            }
        }
    }

    private fun initialize_currency(){
        thb = Currency(0.032, "THB")
        bnd = Currency(0.75, "BND")
        khr = Currency(0.00025, "KHR")
        idr = Currency(0.000068, "IDR")
        lak = Currency(0.00011, "LAK")
        myr = Currency(0.24, "MYR")
        mmk = Currency(0.00071, "MMK")
        php = Currency(0.021, "PHP")
        vnd = Currency(0.000044, "VND")
        sgd = Currency(0.75, "SGD")
        jpy = Currency(0.0092, "JPY")
        cny = Currency(0.15, "CNY")
        gbp = Currency(1.38, "GBP")
        usd = Currency(1.0, "USD")
        eur = Currency(1.20, "EUR")
        currency_list = arrayListOf(
            thb,
            bnd,
            khr,
            idr,
            lak,
            myr,
            mmk,
            php,
            vnd,
            sgd,
            jpy,
            cny,
            gbp,
            usd,
            eur
        )
    }

    private fun setupCustomSpinner(){
        var abbre_from = ""
        var abbre_to = ""
        val adapter = CountryArrayAdapter(this, Countries.list!!)
        //customSpinnerFrom
        customSpinnerFrom.adapter = adapter
        customSpinnerFrom.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent!!.getItemAtPosition(position)
                //Log.d("DEBUG", "ABBRE : ${Countries.list!![position].abbre}")
                Toast.makeText(
                    this@MainActivity,
                    "${Countries.list!![position].name} Selected",
                    Toast.LENGTH_SHORT
                ).show()
                abbre_from = Countries.list!![position].abbre
                Log.d("DEBUG", "From : $abbre_from")
                updateRate()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //customSpinnerTo
        customSpinnerTo.adapter = adapter
        customSpinnerTo.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent!!.getItemAtPosition(position)

                Toast.makeText(
                    this@MainActivity,
                    "${Countries.list!![position].name} Selected",
                    Toast.LENGTH_SHORT
                ).show()
                abbre_to = Countries.list!![position].abbre
                Log.d("DEBUG", "To : $abbre_to")
                updateRate()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }

    private fun updateRate(){
        position_from = customSpinnerFrom.selectedItemPosition
        position_to = customSpinnerTo.selectedItemPosition
        ce = Exchange(currency_list[position_from], currency_list[position_to])
        val rate_textview = findViewById<View>(R.id.rate) as TextView
        rate_textview.setText(
            "Rate : 1 %s = %.5f %s".format(
                currency_list[position_from].abbreviation, ce.convert_currency(
                    1.0
                ),
                currency_list[position_to].abbreviation
            )
        )
        // Text view to set
        val amount_unit_textview = findViewById<View>(R.id.amount_unit) as TextView
        amount_unit_textview.setText(
            "%s".format(currency_list[position_from].abbreviation)
        )


        Log.d("Debug", "Convert :  ${ce.convert_currency(1.0)}")

        Log.d("DEBUG", "From(Updated) : ${customSpinnerFrom.selectedItem}")
        Log.d("DEBUG", "To(Updated) : ${customSpinnerTo.selectedItem}")
        Log.d("DEBUG", "ABBRE - From(Updated) : ${Countries.list!![position_from].abbre}")
        Log.d("DEBUG", "ABBRE - To(Updated) : ${Countries.list!![position_to].abbre}")

    }

}