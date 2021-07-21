package com.felix.backendcaller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.felix.backendcaller.databinding.ActivityMainBinding
import com.felix.backendcaller.models.Student
import com.felix.backendcaller.ui.ItemAdapter
import com.felix.backendcaller.ui.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemAdapter = ItemAdapter(listOf())
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.itemRv.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

        }

        viewModel.run {
            getItems()
            itemsLiveData.observe(this@MainActivity, { items ->
                itemAdapter.items = items
                itemAdapter.notifyDataSetChanged()
            })
        }




        binding.saveBtn.setOnClickListener {
            val student_name = binding.name.text.toString().trim()
            val student_seat = binding.seat.text.toString().trim()
            val student_class = binding.classX.text.toString().trim()

            val student = Student(name = student_name, seat = student_seat, classX =  student_class)

            viewModel.addStudent(student){
                Toast.makeText(applicationContext, it.name+" added Successfully", Toast.LENGTH_LONG).show()
            }
        }
    }
}


