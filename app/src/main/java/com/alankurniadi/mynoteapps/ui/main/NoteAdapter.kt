package com.alankurniadi.mynoteapps.ui.main

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alankurniadi.mynoteapps.R
import com.alankurniadi.mynoteapps.database.Note
import com.alankurniadi.mynoteapps.helper.NoteDiffCallback
import com.alankurniadi.mynoteapps.ui.insert.NoteAddUpdateActivity
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter internal constructor(private val activity: MainActivity): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val listNotes = ArrayList<Note>()
    fun setListNotes(listNotes: List<Note>) {
        Log.e("NoteAdapter", "data NoteAdapter: $listNotes")
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = listNotes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            with(itemView) {
                tv_item_title.text = note.title
                tv_item_date.text = note.date
                tv_item_description.text = note.description

                cv_item_note.setOnClickListener {
                    val intent = Intent(activity, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
                }
            }
        }
    }
}