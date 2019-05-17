package com.example.android.navigation


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
            Navigation.findNavController(view!!).navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
            //view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
            //Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment)
        }

        setHasOptionsMenu(true)
        // this returns a layout
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }
}
