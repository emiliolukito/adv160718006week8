package com.ubaya.adv160718006week8.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.adv160718006week8.R
import com.ubaya.adv160718006week8.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class ToDoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick: (Any)-> Unit): RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>() {
    class ToDoListViewHolder(var view: View):RecyclerView.ViewHolder(view)

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        return ToDoListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.view.checkTask.setText(todoList[position].title.toString())
        
        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, b ->
            adapterOnClick(todoList[position])
        }
    }

}