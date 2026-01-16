package com.bertandfrogs.pixapencil.activities.main.bottomsheet

import com.bertandfrogs.pixapencil.R
import com.bertandfrogs.pixapencil.activities.main.BottomSheetDialog
import com.bertandfrogs.pixapencil.activities.main.MainActivity
import com.bertandfrogs.pixapencil.enums.SnackbarDuration
import com.bertandfrogs.pixapencil.extensions.showSnackbarWithAction
import com.bertandfrogs.pixapencil.models.PixelArt

fun MainActivity.extendedOnRenameTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog) {
    bottomSheetDialog.dismiss()
    pixelArtViewModel.delete(pixelArt)

    binding.activityMainCoordinatorLayout.showSnackbarWithAction(
        getString(R.string.dialog_delete_pixel_art_project_deleted_text, pixelArt.title),
        SnackbarDuration.Long,
        getString(R.string.activityCanvasTopAppMenu_undo)) {
        pixelArtViewModel.insert(pixelArt)
    }
}