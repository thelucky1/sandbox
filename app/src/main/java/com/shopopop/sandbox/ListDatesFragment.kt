package com.shopopop.sandbox


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.TextView
import com.shopopop.sandbox.MainActivity.Companion.RECEIVED_DATES
import kotlinx.android.synthetic.main.fragment_list_dates.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ListDatesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_list_dates, container, false)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args = arguments

        args?.getStringArrayList(RECEIVED_DATES)?.let {
//            val adapter = ArrayAdapter<String>(activity,R.layout.listitem,R.id.textview, args.getStringArrayList(RECEIVED_DATES))
//            list.adapter = adapter

            list.apply {
                Log.i(javaClass.simpleName, "Yuggy $it")
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = MyAdapter(it)
            }
        }

    }


}
