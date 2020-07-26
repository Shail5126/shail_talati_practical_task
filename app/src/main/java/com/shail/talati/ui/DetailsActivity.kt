package com.shail.talati.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.shail.talati.R
import com.shail.talati.utils.SharedPreferance
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var bundle: Bundle? = null
    private var title:String? = null
    private var image:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setInitValues()
    }

    private fun setInitValues(){
        bundle = intent.extras
        if (bundle != null) {
            title = bundle!!.getString("title")
            image = bundle!!.getString("image")
            tvTitle.text = title
            Picasso.get()
                .load(image)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivImage)
        }
        btnLogout.setOnClickListener {
            SharedPreferance.deleteAllPreferences()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            Toast.makeText(this,resources.getString(R.string.str_logout_message), Toast.LENGTH_SHORT).show()

        }
    }
}
