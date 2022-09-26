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
import com.iqbal.challangechapter4.databinding.FragmentEditBinding
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditFragment : Fragment() {

    lateinit var binding : FragmentEditBinding
    var dbNote : NoteDataBases? = null
    lateinit var VmNote : NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_edit, container, false)
        binding = FragmentEditBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        dbNote = NoteDataBases.getInstance(requireContext())
        VmNote = ViewModelProvider(this).get(NoteViewModel::class.java)

        var getData = arguments?.getSerializable("note") as DataNote
        binding.editTitle.setText(getData.title)
        binding.editNotee.setText(getData.content)

        binding.btnEditNote.setOnClickListener{
            editNote()
            Toast.makeText(context, "Edit Notes Success!", Toast.LENGTH_SHORT).show()

        }
    }
        fun editNote(){
            GlobalScope.async {
                var getData = arguments?.getSerializable("note") as DataNote

                var title = binding.editTitle.text.toString()
                var note = binding.editNotee.text.toString()

                val editNote = DataNote(getData.id,title,note)
                VmNote.updateNote(editNote)

                Navigation.findNavController(requireView()).navigate(R.id.action_editFragment_to_homeFragment)
            }
        }

}