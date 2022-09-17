package com.example.groceryapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ActionMode
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var GroceryRV:RecyclerView
    private lateinit var AddGroceryItem: FloatingActionButton
    private lateinit var GroceryList:List<GroceryItems>
    private lateinit var adapter: GroceryAdapter
    lateinit var ViewModel: GroceryViewModel
    lateinit var textView: TextView
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#283a4a")))
        setContentView(R.layout.activity_main)
        GroceryRV=findViewById(R.id.Grocery_items)
        AddGroceryItem=findViewById(R.id.add_grocery)
        textView=findViewById(R.id.Item_Name)
        GroceryList=ArrayList()
        val groceryRepository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(groceryRepository)
        ViewModel = ViewModelProvider(this, factory)[GroceryViewModel::class.java]
        adapter = GroceryAdapter(GroceryList, ViewModel)
        GroceryRV.layoutManager = LinearLayoutManager(this)
        GroceryRV.adapter=adapter
        ViewModel.allGroceryItems().observe(this, {
            adapter.list= it
            adapter.notifyDataSetChanged()
        })
        AddGroceryItem.setOnClickListener {
            GroceryItemDialog(this, object : DialogListener {
                        override fun onAddButtonClicked(item: GroceryItems) {
                            ViewModel.insert(item)
                        }
                    }).show()
        }
    }
}