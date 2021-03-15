package com.garcia.openweatherapp.ui.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.garcia.openweatherapp.R

class CustomDialogFragment(): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Datos no recuperados")
            .setMessage("Hubo un error recuperando los datos. Por favor, revise su conexión a internet.")
            .setPositiveButton("Ok") { _,_ ->
                activity?.findNavController(R.id.mainNavigationFragment)?.navigateUp()
            }
            .create()
    }


    companion object {
        const val TAG = "ErrorDialog"
    }
}