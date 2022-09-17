package com.example.groceryapp

import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import java.lang.Exception

class GroceryItemDialog (context: Context, var dialogListener: DialogListener) : AppCompatDialog(context) {
    private var name:EditText?=null
    private var price:EditText?=null
    private var quantity:EditText?=null
    private var save:Button?=null
    private var cancle:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.grocery_dialog)
        name = findViewById(R.id.Item_Name)
        quantity = findViewById(R.id.Item_Quantity)
        price=findViewById(R.id.Item_Price)
        save= findViewById(R.id.save)
        cancle=findViewById(R.id.cancel)
        name?.filters = arrayOf(
            InputFilter { source, start, end, dest, dstart, dend ->
                return@InputFilter source.replace(Regex("[^a-zA-Z ]*"), "")
            }
        )
        save?.setOnClickListener {
            val itemname= name!!.text.toString()
            val itemquantity= quantity!!.text.toString()
            val itemprice= price!!.text.toString()
            if (itemname.isNotEmpty()&&itemquantity.isNotEmpty()&&itemprice.isNotEmpty()) {
                val qua=itemquantity.toInt()
                val pri=itemprice.toInt()
                val item = GroceryItems(itemname,qua,pri)
                dialogListener.onAddButtonClicked(item)
                dismiss()
            }
            else
                Toast.makeText(context, "Please Enter Item Name", Toast.LENGTH_SHORT).show()
        }

        cancle?.setOnClickListener{
            cancel()
        }
    }
}