package com.rde.android.kotlingmesgresponse

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val SECOND_ACTIVITY = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input_weight.setHint("weight (lbs)")
        input_height.setHint("height (inches)")
        btn_send_data.setOnClickListener {
            val m_intent = Intent(this@MainActivity, SecondActivity::class.java)
            val m_bundle = Bundle()
            m_bundle.putFloat("weight", input_weight.text.toString().toFloat())
            m_bundle.putFloat("height", input_height.text.toString().toFloat())
            m_intent.putExtra("main_activity_data", m_bundle)
            startActivityForResult(m_intent, SECOND_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if((requestCode == SECOND_ACTIVITY) and (resultCode == Activity.RESULT_OK))  {
            val bmi = data?.getFloatExtra("second_activity_data", 1.0F)
            txt_bmi.setText(bmi.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Web")
        menu?.add("Map")
        menu?.add("Phone number")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var m_uri: Uri
        var m_intent: Intent = Intent()
        when (item?.toString()) {
            "Web" -> {
                m_uri = Uri.parse("https://www.apress.com")
                m_intent = Intent(Intent.ACTION_VIEW, m_uri)
            }
            "Map" -> {
             //   m_uri = Uri.parse("geo:40.7113399,-74.0263469")
//      This would have worked as well
       m_uri = Uri.parse("https://maps.google.com/maps?q=40.7113399,-74.0263469")
                m_intent = Intent(Intent.ACTION_VIEW, m_uri)
            }
            "Phone number" -> {
                m_uri = Uri.parse("tel:639285083333")
                m_intent = Intent(Intent.ACTION_DIAL, m_uri)
            }
        }

        startActivity(m_intent)
        return true


    }

}