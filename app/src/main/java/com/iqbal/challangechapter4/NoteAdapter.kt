package com.iqbal.challangechapter4

import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.iqbal.challangechapter4.databinding.ItemNoteBinding
import com.iqbal.challangechapter4.room.DataNote
import com.iqbal.challangechapter4.room.NoteDataBases
import com.iqbal.challangechapter4.room.NoteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NoteAdapter (var listNote : List<DataNote>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    var DBNote : NoteDataBases? = null
    class ViewHolder(var binding : ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.noteId.text = listNote[position].id.toString()
        holder.binding.noteTitle.text = listNote[position].content
        holder.binding.btnDeletNote.setOnClickListener{
            DBNote = NoteDataBases.getInstance(it.context)

            GlobalScope.async {
                NoteViewModel(Application()).deleteNote(listNote[position])

                GlobalScope.async {
                   NoteViewModel(Application()).deleteNote(listNote[position])
                    DBNote?.noteDao()?.deletNote(listNote[position])
                    val nav = Navigation.findNavController(it)
                    nav.run {
                        navigate(R.id.homeFragment)
                    }
                }

//                val del = DBNote?.noteDao()?.deletNote(listNote[position])
//                    (holder.itemView.context as HomeFragment).runOnUiThread{
//                        (holder.itemView.context as HomeFragment).getAllNote()
//                    }
            }
        }
        holder.binding.btnEditNote.setOnClickListener{
          Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editFragment)
        }
//        holder.binding.klik.setOnClickListener{
//            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment)
//            var detail = Intent(it.context,DetailActivity::class.java)
//            detail.putExtra("detail",listNote[position])
//            it.context.startActivity(detail)
//        }
    }


    override fun getItemCount(): Int {
        return listNote.size
    }
    fun setNoteData(listNote: ArrayList<DataNote>)
    {
        this.listNote = listNote
    }
}