package com.rde.android.kotlingmesgresponse

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val bundle = intent.getBundleExtra("main_activity_data")
        val height = bundle!!.getFloat("height")
        val weight = bundle.getFloat("weight")
        txt_intentdata.text = "Height: $height | Weight: $weight"
        btn_calculate.setOnClickListener {
            val m_intent = Intent()
            val m_bmi = 703 * (weight / (height * height))
            m_intent.putExtra("second_activity_data", m_bmi)
            (this@SecondActivity).setResult(Activity.RESULT_OK, m_intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.menuFile -> {
                showMessage("File menu")
                return true
            }
            R.id.menuEdit -> {
                showMessage("Edit menu")
                return true
            }
            R.id.menuHelp -> {
                showMessage("Help menu")
                return true
            }
            R.id.menuExit -> {
                showMessage("Exit")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showMessage(msg:String) {
        Snackbar.make(rootLayout, msg, Snackbar.LENGTH_LONG).show()
    }

}