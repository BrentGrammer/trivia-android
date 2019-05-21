/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ShareActionProvider
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)

        binding.nextMatchButton.setOnClickListener {
            //Navigation.createNavigateOnClickListener(R.id.action_gameWonFragment_to_gameFragment)
            Navigation.findNavController(view!!).navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())

        }

        //
        var args = GameWonFragmentArgs.fromBundle(arguments!!)


        Toast.makeText(context,
                "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}, Thirdarg: ${args.thirdArg}",
                 Toast.LENGTH_LONG).show()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)

        // check if any supported activities exist to avoid crashing:
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    private fun getShareIntent() : Intent {
        var args = GameWonFragmentArgs.fromBundle(arguments!!)

        // This is a more verbose way of setting up the shared intent
        //val shareIntent = Intent(Intent.ACTION_SEND)
//        shareIntent.setType("text/plain")
//                .putExtra(Intent.EXTRA_TEXT,
//                    getString(R.string.share_success_text, args.numCorrect,
//                            args.numQuestions))
//        return shareIntent

        // Since sharing is very common, you can use ShareCompat helper and you don't need to know what Extra (i.e. EXTRA_TEXT) to use:
        return ShareCompat.IntentBuilder.from(activity)
                .setText(getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
                //.setType("text/plain")
                .intent // this finally builds the intent

    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            // id of the winner menu defined in winner_menu.xml
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

}
