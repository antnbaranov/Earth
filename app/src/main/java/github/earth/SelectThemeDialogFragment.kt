package github.earth

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import github.earth.utils.*

class SelectThemeDialogFragment: DialogFragment(), View.OnClickListener {

    private lateinit var themeDef        : View
    private lateinit var themePurple     : View
    private lateinit var themePink       : View
    private lateinit var themeGreen      : View
    private lateinit var themeRed        : View
    private lateinit var themeBeige      : View
    private lateinit var themeLightPink  : View
    private lateinit var themeYellow     : View
    private lateinit var themeBlue       : View
    private lateinit var themeGray       : View
    private lateinit var themeOrange     : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_theme_dialog, container)

        themeDef        = rootView.findViewById(R.id.themeDef)
        themePurple     = rootView.findViewById(R.id.themePurple)
        themePink       = rootView.findViewById(R.id.themePink)
        themeGreen      = rootView.findViewById(R.id.themeGreen)
        themeBlue       = rootView.findViewById(R.id.themeBlue)
        themeBeige      = rootView.findViewById(R.id.themeBeige)
        themeLightPink  = rootView.findViewById(R.id.themeLightPink)
        themeRed        = rootView.findViewById(R.id.themeRed)
        themeYellow     = rootView.findViewById(R.id.themeYellow)
        themeGray       = rootView.findViewById(R.id.themeGray)
        themeOrange     = rootView.findViewById(R.id.themeOrange)

        listenSetter()

        return rootView
    }

    override fun onClick(v: View?) {
        Toast.makeText(requireContext(), R.string.choice, Toast.LENGTH_SHORT).show()
        when (v?.id) {
            R.id.themeDef       -> (activity as MainActivity).changeIcon(IC_DEFAULT)
            R.id.themePurple    -> (activity as MainActivity).changeIcon(IC_PURPLE)
            R.id.themePink      -> (activity as MainActivity).changeIcon(IC_PINK)
            R.id.themeGreen     -> (activity as MainActivity).changeIcon(IC_GREEN)
            R.id.themeBlue      -> (activity as MainActivity).changeIcon(IC_BLUE)
            R.id.themeOrange    -> (activity as MainActivity).changeIcon(IC_ORANGE)
            R.id.themeGray      -> (activity as MainActivity).changeIcon(IC_GRAY)
            R.id.themeYellow    -> (activity as MainActivity).changeIcon(IC_YELLOW)
            R.id.themeRed       -> (activity as MainActivity).changeIcon(IC_RED)
            R.id.themeLightPink -> (activity as MainActivity).changeIcon(IC_LIGHT_PINK)
            R.id.themeBeige     -> (activity as MainActivity).changeIcon(IC_BEIGE)
            else -> Toast.makeText(requireContext(), "Nothing selected", Toast.LENGTH_SHORT).show()
        }
        dismiss()
    }

    private fun listenSetter() {
        themeDef.setOnClickListener(this)
        themePurple.setOnClickListener(this)
        themePink.setOnClickListener(this)
        themeGreen.setOnClickListener(this)
        themeBlue.setOnClickListener(this)
        themeOrange.setOnClickListener(this)
        themeGray.setOnClickListener(this)
        themeYellow.setOnClickListener(this)
        themeRed.setOnClickListener(this)
        themeLightPink.setOnClickListener(this)
        themeBeige.setOnClickListener(this)

    }

}