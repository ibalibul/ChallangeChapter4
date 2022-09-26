package com.iqbal.challangechapter4.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.iqbal.challangechapter4.R
import com.iqbal.challangechapter4.databinding.FragmentAddNoteBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddNoteFragment : Fragment() {

    lateinit var binding : FragmentAddNoteBinding
    var dbNote : NoteDataBases? = null
    lateinit var VmNote : NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
        binding = FragmentAddNoteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbNote = NoteDataBases.getInstance(requireContext())
        VmNote = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.btnSaveNote.setOnClickListener{
            addNote()
            Navigation.findNavController(view).navigate(R.id.action_addNoteFragment_to_homeFragment)
        }

    }

    fun addNote(){
        GlobalScope.async {
            var title = binding.noteTitle.text.toString()
            var note  = binding.noteContent.text.toString()
            val newNote = DataNote(0,title,note)
            VmNote.insertNote(newNote)
            Toast.makeText(context,"Notes Success", Toast.LENGTH_SHORT).show()
        }
    }
}