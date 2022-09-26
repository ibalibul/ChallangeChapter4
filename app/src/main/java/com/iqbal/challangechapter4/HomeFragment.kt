package com.iqbal.challangechapter4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.iqbal.challangechapter4.databinding.FragmentHomeBinding
import com.iqbal.challangechapter4.room.DataNote
import com.iqbal.challangechapter4.room.NoteDataBases
import com.iqbal.challangechapter4.room.NoteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    var NoteDB : NoteDataBases? =  null
    lateinit var adapter: NoteAdapter
    lateinit var noteViewModel : NoteViewModel
    lateinit var sharedPreferces : SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferces = requireActivity().getSharedPreferences("Userdata",Context.MODE_PRIVATE)



        NoteDB = NoteDataBases.getInstance(requireContext())

        noteVm()

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNoteObservers().observe(requireActivity(), Observer{
            adapter.setNoteData(it as ArrayList<DataNote>)
        })

        binding.btnAddNote.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_editFragment)
        }
        binding.btnlogout.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment)
        }

    }


    fun noteVm(){
        adapter = NoteAdapter(ArrayList())
        binding.rvNote.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvNote.adapter = adapter
    }

    fun getAllNote(){

        GlobalScope.launch {
            var data = NoteDB?.noteDao()?.getDataNote()
            activity?.runOnUiThread{
                adapter = NoteAdapter(data!!)
                binding.rvNote.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = adapter
            }
        }

    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            var data = NoteDB?.noteDao()?.getDataNote()
            activity?.runOnUiThread{
                adapter = NoteAdapter(data!!)
                binding.rvNote.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = adapter
            }

        }
    }

}