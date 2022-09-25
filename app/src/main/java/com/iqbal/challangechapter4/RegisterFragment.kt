package com.iqbal.challangechapter4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.iqbal.challangechapter4.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {

    lateinit var binding : FragmentRegisterBinding
    lateinit var sharedPreferces : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_register, container, false)
        binding =  FragmentRegisterBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferces = requireActivity().getSharedPreferences("Userdata",Context.MODE_PRIVATE)

        btnRegister.setOnClickListener{
            var getUsername = binding.etUserName.text.toString()
            var getEmail = binding.etEmail.text.toString()
            var getPassword = binding.etPassword.text.toString()
            var getReapetPass = binding.etRepeatPassword.text.toString()

            if(getPassword.equals(getReapetPass)){
                var addUser = sharedPreferces.edit()
                addUser.putString("username", getUsername)
                addUser.putString("email", getEmail)
                addUser.putString("password", getPassword)
                addUser.apply()
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)

            }
            else Toast.makeText(context, "Password Invalid", Toast.LENGTH_SHORT).show()
        }
        Register.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
        }

    }

}