package com.shopopop.sandbox

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.app.Activity
import android.net.Uri
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.shopopop.sandbox.InputActivity.Companion.SENT_MESSAGE
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener, DateInsertFragment.Listener {
    companion object {
        const val RECEIVED_DATES = "received_dates"
    }
    var arrayDates = ArrayList<String>()

    override fun onFragmentInteraction(uri: Uri) {
        Log.i(javaClass.simpleName, "onFragmentInteraction")
    }

    override fun onAddDate(date: String) {
        arrayDates.add(date)
        navigation.selectedItemId = R.id.navigation_notifications
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentPlaceholder, HomeFragment())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentPlaceholder, DateInsertFragment())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val bundle = Bundle()
                val f = ListDatesFragment()
                bundle.putStringArrayList(RECEIVED_DATES, arrayDates)
                f.arguments = bundle
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentPlaceholder, f)
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_home

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val receivedMessage = data.getStringExtra(SENT_MESSAGE)

                Log.e(javaClass.simpleName, "Message sent yay ${receivedMessage}")
                Toast.makeText(this@MainActivity, "The message is ${receivedMessage}", Toast.LENGTH_LONG).show()
                val receivedMessageText = findViewById<TextView>(R.id.receivedMessage)
                receivedMessageText.visibility = View.VISIBLE
                receivedMessageText.text = receivedMessage
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result

            }
        }
    }//onActivityResult
}
