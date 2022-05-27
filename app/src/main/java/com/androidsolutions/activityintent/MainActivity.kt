package com.androidsolutions.activityintent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.androidsolutions.activityintent.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initClicks()
    }

    private fun initClicks() {
        binding.btnString.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java).apply {
                putExtra("id", 5)
                putExtra("username", "AndroidSolutions")
                putExtra("type", "Pass String")
            })
        }
        binding.btnObject.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java).apply {
                putExtra("dataObj", User("AndroidSolutions User", 27))
                putExtra("type", "Pass Object")
            })
        }
        binding.btnList.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java).apply {
                putParcelableArrayListExtra(
                    "dataList",
                    arrayListOf(Employee(1, "Android"), Employee(2, "Solutions"))
                )
                putExtra("type", "Pass List")
            })
        }
        binding.btnKotlinBundle.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java).apply {
                putExtras(
                    bundleOf(
                        "id" to 6, "username" to "AndroidSolutions2",
                        "type" to "Kotlin Bundle"
                    )
                )
            })
        }
        binding.btnConverted.setOnClickListener {
            val asObj =
                AndroidSolution(Employee(1, "Android"), Student(1, "Android Solution Student"))
            val asObjAsString = Gson().toJson(asObj)
            startActivity(Intent(this, DetailActivity::class.java).apply {
                putExtra("dataGson", asObjAsString)
                putExtra("type", "Pass Converted")
            })
        }
    }
}