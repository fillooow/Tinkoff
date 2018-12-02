package com.example.su.tinkoff


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_orders.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OrdersFragment : Fragment(), ShortOrdersAdapter.OnRecyclerViewItemClickedListener{

    val cladmenApiService by lazy {
        CladmenApiService.create()
    }

    companion object {

        val API_KEY = "845e2478-1fa7-4253-9580-111b2c959e50"
    }

    var disposable: Disposable? = null
    private lateinit var rvOrders: RecyclerView
    private lateinit var ordersList: ArrayList<ShortOrder>
    private lateinit var fullOrderList: ArrayList<MyModel.MyResult>
    private lateinit var shortOrder: ShortOrder
    private lateinit var adapter: ShortOrdersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_orders, container, false)


        rvOrders = rootView.findViewById(R.id.recyclerViewOrders)
        rvOrders.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)


        ordersList = ArrayList()
        fullOrderList = ArrayList()
        var shortOrder = ShortOrder("Екатеринбург, Мира 32", "+79222235522", "15:18")
        ordersList.add(shortOrder)
        ordersList.add(ShortOrder("Екатеринбург, Ленина 48", "+79222234431", "16:18"))

        adapter = ShortOrdersAdapter(ordersList, this)
        rvOrders.adapter = adapter





        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //beginSync()
    }

    override fun onResume() {
        super.onResume()
        //beginSync()
    }

    override fun onStart() {
        super.onStart()
        beginSync()
    }

    override fun onClickImageButton(param: Int) {
        activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, MapsFragment())
                .addToBackStack(null)
                .commit()
    }

    override fun onClickCard(param: Int) {
        activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, OrderInfoFragment())
                .addToBackStack(null)
                .commit()
    }

    fun beginSync(){
        disposable = cladmenApiService
                .sync(API_KEY, 0, 56.8410384f, 60.6500373f)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Toast.makeText(activity, "Is new orders: ${it.needSync}", Toast.LENGTH_SHORT).show()
                    getMy()
                }
    }

    fun getMy(){
        disposable = cladmenApiService
                .my(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    for (item in it){
                        fullOrderList.add(item)
                        ordersList.add(
                                ShortOrder(item.name ?: "Имя не указано",
                                item.phone ?: "телефон не указан",
                                item.dateTime))
                    }
                    adapter.notifyDataSetChanged()
                }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }
}
