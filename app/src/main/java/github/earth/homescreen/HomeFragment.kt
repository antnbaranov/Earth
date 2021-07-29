package github.earth.homescreen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import github.earth.R
import github.earth.authscreen.LoginActivity

class HomeFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)


        mAuth = FirebaseAuth.getInstance()


        if (mAuth.currentUser == null) {
            val intent_toLogin = Intent (getActivity(), LoginActivity::class.java)
            getActivity()?.startActivity(intent_toLogin)
            getActivity()?.finish();

        }

        return rootView
    }


}