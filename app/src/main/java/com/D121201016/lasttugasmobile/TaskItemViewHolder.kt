package com.D121201016.lasttugasmobile

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.D121201016.lasttugasmobile.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener
): RecyclerView.ViewHolder(binding.root)
{
    val timeFormat = DateTimeFormatter.ofPattern( "HH:mm")
    fun bindTaskItem(taskItem: TaskItem)
    {
        binding.desc.text = taskItem.desc

        if(taskItem.isCompleted()){
            binding.desc.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completeButton.setImageResource(taskItem.imageResource())
        binding.completeButton.setColorFilter(taskItem.imageColor(context))

        binding.completeButton.setOnClickListener{
            clickListener.completeTaskItem(taskItem)
        }
        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItem(taskItem)
        }


        if(taskItem.dueTime() != null)
            binding.dueTime.text = timeFormat.format(taskItem.dueTime())
        else
            binding.dueTime.text = ""
    }
}