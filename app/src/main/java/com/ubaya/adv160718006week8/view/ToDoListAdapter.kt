package com.ubaya.adv160718006week8.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.adv160718006week8.R
import com.ubaya.adv160718006week8.databinding.TodoItemLayoutBinding
import com.ubaya.adv160718006week8.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class ToDoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick: (Any)-> Unit): RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>(),
    TodoCheckedChangeListener, TodoEditClickListener {
    class ToDoListViewHolder(var view: TodoItemLayoutBinding):RecyclerView.ViewHolder(view.root)

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater, R.layout.todo_item_layout,
            parent,false)
        return ToDoListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.view.todo = todoList[position]
        holder.view.listener = this
        holder.view.editlistener = this
        /*holder.view.checkTask.setText(todoList[position].title.toString() + "-"+ todoList[position].priority)

        holder.view.imgEdit.setOnClickListener {
            val action = TodoListFragmentDirections.actionEdit(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked)
            {
                adapterOnClick(todoList[position])
            }
        }

         */
    }

    override fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked)
        {
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val action = TodoListFragmentDirections.actionEdit(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

}