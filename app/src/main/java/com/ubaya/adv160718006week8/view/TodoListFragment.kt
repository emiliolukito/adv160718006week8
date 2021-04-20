package com.ubaya.adv160718006week8.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.adv160718006week8.R
import com.ubaya.adv160718006week8.model.Todo
import com.ubaya.adv160718006week8.viewmodel.ListToDoViewModel
import kotlinx.android.synthetic.main.fragment_todo_list.*
import java.security.acl.Owner

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodoListFragment : Fragment() {

    private lateinit var viewModel:ListToDoViewModel
    private var toDoListAdapter:ToDoListAdapter = ToDoListAdapter(arrayListOf(), { item -> doClick(item) })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListToDoViewModel::class.java)
        viewModel.refresh()
        recViewToDoList.layoutManager = LinearLayoutManager(context)
        recViewToDoList.adapter = toDoListAdapter
        fabAdd.setOnClickListener {
            val action = TodoListFragmentDirections.actionCreate()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    fun observeViewModel()
    {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            toDoListAdapter.updateTodoList(it)
            if (it.isEmpty())
            {
                txtEmpty.visibility = View.VISIBLE
            }
            else
            {
                txtEmpty.visibility = View.GONE
            }
        })
    }

    fun doClick(item:Any)
    {
        var item2 = item as Todo
        viewModel.isDone(item2.uuid)
    }
}