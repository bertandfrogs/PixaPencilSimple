package com.bertandfrogs.pixapencil.activities.main.bottomsheet

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bertandfrogs.pixapencil.R
import com.bertandfrogs.pixapencil.activities.main.BottomSheetDialog
import com.bertandfrogs.pixapencil.activities.main.MainActivity
import com.bertandfrogs.pixapencil.databinding.NameProjectAlertBinding
import com.bertandfrogs.pixapencil.extensions.hideSoftInput
import com.bertandfrogs.pixapencil.models.PixelArt
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun MainActivity.extendedOnRenameTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog) {
    val nameProjectAlertBinding = NameProjectAlertBinding.inflate(LayoutInflater.from(this))

    nameProjectAlertBinding.root.post {
        // We do this so that there is no default top margin, which I personally find it ugly
        (nameProjectAlertBinding.root.layoutParams as ViewGroup.MarginLayoutParams).topMargin = 0
    }

    val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
        .setTitle(R.string.activityMain_rename_pixel_art_str)
        .setView(nameProjectAlertBinding.root)
        .setPositiveButton(R.string.generic_ok) { _, _ ->
            nameProjectAlertBinding.nameProjectAlertNameTextInputEditText.hideSoftInput()

            if (nameProjectAlertBinding.nameProjectAlertNameTextInputEditText.text?.isNotEmpty() == true) {
                val newTitle = nameProjectAlertBinding.nameProjectAlertNameTextInputEditText.text.toString()
                if (newTitle.isNotBlank() && newTitle.length <= 50) {
                    pixelArt.title = newTitle
                    pixelArtViewModel.update(pixelArt)
                    bottomSheetDialog.dismiss()
                }
            }
        }
        .setNegativeButton(R.string.generic_cancel, null)

    alertDialog.show()
}