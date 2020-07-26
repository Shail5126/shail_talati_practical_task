package com.shail.talati.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shail.talati.R
import com.shail.talati.adapter.ListDetailsAdapter
import com.shail.talati.listener.OnItemClickListener
import com.shail.talati.model.Details
import com.shail.talati.model.ListInterface
import com.shail.talati.model.ServiceBuilder
import com.shail.talati.utils.SharedPreferance
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(),OnItemClickListener {

    private var listAdapter: ListDetailsAdapter? = null
    private var list: ArrayList<Details>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val request = ServiceBuilder.buildService(ListInterface::class.java)
        val call = request.getListDetails()

        call.enqueue(object : Callback<List<Details>> {
            override fun onResponse(call: Call<List<Details>>, response: Response<List<Details>>) {
                if (response.isSuccessful) {
                    progress_bar.visibility = View.GONE
                    setDetails(response)
                }
            }

            override fun onFailure(call: Call<List<Details>>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setDetails(response: Response<List<Details>>) {
        list = arrayListOf()
        list!!.addAll((response.body() as ArrayList<Details>?)!!)
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        listAdapter = ListDetailsAdapter(this, list!!,this)
        recyclerView.adapter = listAdapter
        listAdapter!!.setData(list!!)
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("title",list!![position].title)
        intent.putExtra("image",list!![position].thumbnailUrl)
        startActivity(intent)
    }
}
