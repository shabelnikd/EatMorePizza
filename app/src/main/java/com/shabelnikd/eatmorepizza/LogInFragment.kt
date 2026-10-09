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


class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(binding.root)

        binding.btnLogIn.setOnClickListener {
            navController.navigate(R.id.action_LogIn_to_MainScreen)
        }

        binding.tvAccountHaveClick.setOnClickListener{
            navController.navigate(R.id.action_LogIn_to_Register)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LogInFragment()
    }
}