package com.alankurniadi.mynoteapps.ui.insert

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.alankurniadi.mynoteapps.database.Note
import com.alankurniadi.mynoteapps.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun insert(note: Note) {
        try {
            mNoteRepository.insert(note)
            Log.e("NoteAddUpdateViewModel", "Insert: $mNoteRepository")
        }catch (e: Exception) {
            e.printStackTrace()
            Log.e("NoteAddUpdateViewModel","Insert catch: ${e.message}")
        }

    }

    fun update(note: Note) {
        mNoteRepository.update(note)
    }

    fun delete(note: Note) {
        mNoteRepository.delete(note)
    }

}