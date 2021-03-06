package com.ubaya.adv160718006week8.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.adv160718006week8.R
import com.ubaya.adv160718006week8.databinding.FragmentEditToDoBinding
import com.ubaya.adv160718006week8.model.Todo
import com.ubaya.adv160718006week8.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_to_do.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditFragment : Fragment(), RadioClickListener, SaveChangesListener {
    private lateinit var viewModel:DetailTodoViewModel
    private lateinit var dataBinding:FragmentEditToDoBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditToDoBinding>(inflater, R.layout.fragment_edit_to_do, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        var uuid = EditFragmentArgs.fromBundle(requireArguments()).uuid

        viewModel.fetch(uuid)
        dataBinding.radiolistener = this
        dataBinding.listener = this
        /*btnCreate.setOnClickListener {
            var radio = view.findViewById<RadioButton>(rgPriority.checkedRadioButtonId)
            viewModel.updateTodo(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Data Updated", Toast.LENGTH_SHORT).show()
        }*/

        observeViewModel()
    }

    fun observeViewModel()
    {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
            /*txtTitle.setText(it.title)
            txtNotes.setText(it.notes)
            if(it.priority==3)
            {
                radioHigh.isChecked = true
            }
            else if (it.priority==2)
            {
                radioMedium.isChecked = true
            }
            else
            {
                radioLow.isChecked = true
            }*/
        })
    }

    override fun onRadioClick(v: View, obj: Todo) {
        obj.priority = v.tag.toString().toInt()
    }

    override fun onSaveChanges(v: View, obj: Todo) {
        Log.d("cek",obj.toString())
        viewModel.updateTodo(obj.title,obj.notes,obj.priority,obj.uuid)
        Toast.makeText(v.context, "Todo updated", Toast.LENGTH_SHORT).show()
    }
}