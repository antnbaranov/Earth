package github.earth.placesscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import github.earth.R

class PlacesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_places, container, false)

        //Навигация дальше
//        view.findViewById<Button>(R.id.signup_btn).setOnClickListener {
//            findNavController().navigate(R.id.action_register_to_registered)
//        }
        return view
    }
}