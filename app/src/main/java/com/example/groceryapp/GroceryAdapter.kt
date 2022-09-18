package com.example.groceryapp

import android.view.*
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.FragmentActivity




class GroceryAdapter(var list: List<GroceryItems>, val viewModel: GroceryViewModel) :
    RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>(){
    private lateinit var actionmode: ActionMode
    private var isEnable=false
    private var isSelectAll=false
    var selectList: List<GroceryItems> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_item, parent, false)
        return GroceryViewHolder(view)
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val currentPosition = list[position]
        holder.ItemName.text = currentPosition.itemName
        holder.ItemPrice.text = currentPosition.itemPrice.toString()
        holder.ItemQuantity.text = currentPosition.itemQuantity.toString()
        holder.itemView.setOnLongClickListener {
            val i=gettingposition(position)
            actionmode = it.startActionMode(i)
            actionmode.title = "selected"
            return@setOnLongClickListener true
        }
    }

    private fun gettingposition(position: Int):ActionMode.Callback {
        val callback = object : ActionMode.Callback{

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                mode!!.menuInflater.inflate(R.menu.top_app_bar, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.delete -> {
                        viewModel.delete(list[position])
                        mode!!.finish()
                        true
                    }
                    else -> false
                }
            }
            override fun onDestroyActionMode(mode: ActionMode?) {
            }
        }
        return callback
    }
    inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var ItemName: TextView = itemView.findViewById(R.id.Item_Name)
        var ItemPrice: TextView = itemView.findViewById(R.id.Item_Price)
        var ItemQuantity: TextView = itemView.findViewById(R.id.Item_Quantity)
    }


}