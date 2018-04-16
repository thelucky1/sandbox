package com.shopopop.sandbox


import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.R.attr.startYear
import android.net.Uri
import android.text.SpannableStringBuilder
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_date_insert.*
import java.util.*
import android.R.attr.data
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.text.SimpleDateFormat
import java.util.concurrent.Executors


/**
 * A simple [Fragment] subclass.
 *
 */
class DateInsertFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    var listener: Listener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date_insert, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(context, this, year, month, day)

        selectedDate.setOnClickListener {
            datePickerDialog.show()
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user
        val date = "$day - $month - $year"
        selectedDate.text = SpannableStringBuilder(date)
        btnShowCalendar.setOnClickListener {
            //            AsyncTaskExample(date).execute()

            val executor = Executors.newCachedThreadPool()
            val handler = Handler(Looper.getMainLooper())
            val worker = Runnable {
                Thread.sleep(5000)
//                activity?.runOnUiThread {
//                    listener?.onAddDate(date)
//                    Log.d(javaClass.simpleName, "lala")
//                }
                handler.post({
                    listener?.onAddDate(date)
                    Log.d(javaClass.simpleName, "lala")
                })
            }
            executor.execute(worker)


//            executor.shutdown()
//            while (!executor.isTerminated) {
//            }

            Toast.makeText(activity, "Successfully Added: $date", Toast.LENGTH_LONG).show()
        }
    }

    inner class AsyncTaskExample(val date: String) : AsyncTask<String, Int, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(javaClass.simpleName, "onPreExecute yay")
        }

        override fun doInBackground(vararg params: String?): String? {
            Thread.sleep(5000)

            Log.d(javaClass.simpleName, "doInBackground params $params")
            return "lala"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            listener?.onAddDate(date)
            Log.d(javaClass.simpleName, "onPostExecute $result")
        }

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as Listener
    }

    interface Listener {
        fun onAddDate(date: String)
    }

    override fun onDetach() {
        super.onDetach()
    }

}
