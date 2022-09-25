package com.iqbal.challangechapter4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.iqbal.challangechapter4.databinding.FragmentSpalshScreenBinding


class SpalshScreenFragment : Fragment() {

    lateinit var binding : FragmentSpalshScreenBinding
    lateinit var sharedPref : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_spalsh_screen, container, false)
        binding =  FragmentSpalshScreenBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//      Untuk memanggil SharedpPrefrences
        sharedPref = requireActivity().getSharedPreferences("Userdata",Context.MODE_PRIVATE)
        var dataUser = sharedPref.getString("username","")

        if (dataUser != "") {
        var namalengkap = sharedPref.getString("NamaLengkap","anda")
        var bundle =  Bundle()
            bundle.putString("NamaLengkap",namalengkap)
            Handler().postDelayed({
                Navigation.findNavController(view).navigate(R.id.action_spalshScreenFragment_to_homeFragment, bundle)
            }, 3000)
        }
        else {
            Handler().postDelayed({
                Navigation.findNavController(view).navigate(R.id.action_spalshScreenFragment_to_loginFragment)
            }, 3000)
        }
    }

}