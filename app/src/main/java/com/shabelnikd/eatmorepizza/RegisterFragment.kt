package com.shabelnikd.eatmorepizza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.shabelnikd.eatmorepizza.databinding.FragmentLogInBinding
import com.shabelnikd.eatmorepizza.databinding.FragmentMainScreenBinding
import com.shabelnikd.eatmorepizza.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(binding.root)

        binding.btnLogIn.setOnClickListener{
            navController.navigate(R.id.action_Register_to_MainScreen)
        }

        binding.backBtn.setOnClickListener{
            navController.navigate(R.id.action_Register_to_LogIn)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RegisterFragment()
    }
}