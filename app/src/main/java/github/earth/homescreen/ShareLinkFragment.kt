package github.earth.homescreen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.DialogTitle
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import github.earth.MainActivity
import github.earth.R
import github.earth.models.User
import github.earth.utils.*
import kotlinx.android.synthetic.main.fragment_shareinfo.*
import kotlinx.android.synthetic.main.fragment_sharelink.*
import kotlinx.android.synthetic.main.fragment_sharephoto.*

class ShareLinkFragment : Fragment(R.layout.fragment_sharephoto) {


    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).homeViewModel

        updatingPostImage()

        nextbuttonone.setOnClickListener {
            uploadTutorial()
        }

        viewModel.uploadTutorialState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                    this.findNavController()
                        .navigate(R.id.action_SharePhotoScreen_to_HomeFragment)
                    viewModel.doneTutorialImageUri()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
            viewModel.doneTutorialState()
        })

//        ivBackBtn.setOnClickListener {
//            Toast.makeText(activity, "Tutorial Creation Cancelled", Toast.LENGTH_SHORT).show()
//            this.findNavController().navigateUp()
//            viewModel.doneTutorialImageUri()
//        }
    }

    private fun updatingPostImage() {
        ivTutorialImageShare.setOnClickListener {
            pickImageFromGallery()
        }

        viewModel.tutorialImageUri.observe(viewLifecycleOwner, Observer {
            it?.let {
                Glide.with(this).load(it).into(ivTutorialImageShare)
            }
        })
        //Отображение профиля при onCreate
//        viewModel.profileImageUri.observe(viewLifecycleOwner, Observer {
//            Glide.with(this).load(it).into(ivProfileImage)
//        })
    }

    //todo как тест все без bundle
    private fun uploadTutorial() {
        val title = etTutorialTitle.text.toString()
        val materials = etTutorialMaterials.text.toString()
        val time = etTutorialTime.text.toString().toInt()
        val description = etTutorialDescription.text.toString()
        val link = etTutorialLink.text.toString()

        viewModel.uploadTutorialDetailsToFirestore(title, materials, time, description, link)
    }

    //вынести на финальный проект.
    private fun showProgressBar() {
        nextbuttonone.visibility = View.INVISIBLE
//        createProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        nextbuttonone.visibility = View.VISIBLE
//        createProgressBar.visibility = View.INVISIBLE
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Constants.IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.IMAGE_PICK_CODE) {
            data?.data?.let {
                viewModel.setTutorialImageUri(it)
            }
        }
    }

}