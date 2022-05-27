package com.androidsolutions.activityintent

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.androidsolutions.activityintent.databinding.ActivityDetailBinding
import com.google.gson.Gson


class DetailActivity : AppCompatActivity() {

    private val type by lazy { intent?.extras?.getString("type", "") }
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.apply {
            title = type
            setDisplayHomeAsUpEnabled(true)
        }
        parseInputs()
    }

    private fun parseInputs() {
        when (type) {
            "Pass String" -> setValues(
                intent?.extras?.getInt("id"),
                intent?.extras?.getString("username"),
            )
            "Pass Object" -> {
                (intent?.extras?.getSerializable("dataObj") as User).let {
                    setValues(it.id, it.name)
                }
            }
            "Pass List" -> {
                (intent?.extras?.getParcelableArrayList<Employee>("dataList"))?.let {
                    if (it.isNotEmpty())
                        setValues(it[0].id, it[0].name)
                }
            }
            "Kotlin Bundle" -> {
                intent?.extras?.let {
                    setValues(it.getInt("id"), it.getString("username"))
                }
            }
            "Pass Converted" -> {
                val asObjAsString = intent?.extras?.getString("dataGson")
                val asObj = Gson().fromJson(asObjAsString, AndroidSolution::class.java)
                Log.d("DetailActivity", "parseInputs: asObjAsString $asObjAsString")
                setValues(asObj.student.id, asObj.student.name)
            }
        }
    }

    private fun setValues(_id: Int?, _name: String?) {
        binding.tvID.text = _id.toString()
        binding.tvName.text = _name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}