package harshbarash.github.room.ui.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import harshbarash.github.room.R
import harshbarash.github.room.databinding.FragmentAddBinding
import harshbarash.github.room.model.Task
import harshbarash.github.room.viewModel.TaskViewModel

class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var _binding: FragmentAddBinding
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddBinding.bind(view)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        _binding.btnCreate.setOnClickListener {
            insertDataToDatabase()
        }

        _binding.ivBackBtn.setOnClickListener {
            _binding.ivBackBtn.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun insertDataToDatabase() {
       val task = _binding.etTask.text.toString()
       val description = _binding.etDesc.text.toString()
       val point = _binding.etPoint.text.toString().toInt()

        if(inputCheck(task, description, point)) {
            val task = Task(title  = task, description = description, point = point)
            mTaskViewModel.addTask(task)
            view?.let { Snackbar.make(it, "Задача поставлена", Snackbar.LENGTH_LONG).show() }
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            view?.let { Snackbar.make(it, "Поставьте задачу", Snackbar.LENGTH_LONG).show() }
        }
    }

    private fun inputCheck(task: String, description: String, point: Int): Boolean{
        return !TextUtils.isEmpty(task) && (point >= 0)
    }
}

