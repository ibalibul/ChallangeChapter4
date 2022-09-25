package com.iqbal.challangechapter4.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(app : Application) : AndroidViewModel(app) {

    lateinit var allNote : MutableLiveData<List<DataNote>>

    init {
        allNote = MutableLiveData()
        getAllNote()
    }
    fun getAllNoteObservers(): MutableLiveData<List<DataNote>> {
        return allNote
    }

    fun getAllNote() {
        GlobalScope.launch {
            val userDao = NoteDataBases.getInstance(getApplication())!!.noteDao()
            val listnote = userDao.getDataNote()
            allNote.postValue(listnote)
        }
    }
    fun insertNote(entity: DataNote){
        val noteDao = NoteDataBases.getInstance(getApplication())?.noteDao()
        noteDao!!.insertNote(entity)
        getAllNote()
    }

    fun deleteNote(entity: DataNote){
        val userDao = NoteDataBases.getInstance(getApplication())!!.noteDao()
        userDao?.deletNote(entity)
        getAllNote()
    }

    fun updateNote(entity: DataNote){
        val userDao = NoteDataBases.getInstance(getApplication())!!.noteDao()
        userDao?.updateNote(entity)
        getAllNote()
    }
}