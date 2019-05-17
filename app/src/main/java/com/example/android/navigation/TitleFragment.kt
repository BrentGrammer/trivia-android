package com.example.android.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.android.navigation.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // there is no set content call in Fragments - this uses the binding util to inflate the fragment and return a layout
        // container is a view group the fragment will be hosted by (provided)
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)

        binding.playButton.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_titleFragment_to_gameFragment)
            //view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
            //Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment)

        }

        // this returns a layout
        return binding.root
    }


}
