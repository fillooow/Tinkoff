package com.example.su.tinkoff


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_orders.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class OrdersFragment : Fragment() {

    private lateinit var rvOrders: RecyclerView
    private lateinit var ordersList: ArrayList<ShortOrder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_orders, container, false)


        rvOrders = rootView.findViewById(R.id.recyclerViewOrders)
        rvOrders.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)


        ordersList = ArrayList()
        var shortOrder = ShortOrder("Улица пушкина", "14", "Сегодня")
        ordersList.add(shortOrder)
        shortOrder = ShortOrder("Дом колотушкина", "88", "Завтра")
        ordersList.add(shortOrder)

        val adapter = ShortOrdersAdapter(ordersList)
        rvOrders.adapter = adapter

        return rootView
    }


}
