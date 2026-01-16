/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.bertandfrogs.pixapencil.activities.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.takusemba.spotlight.Spotlight
import com.bertandfrogs.pixapencil.activities.main.bottomsheet.extendedOnDeleteTapped
import com.bertandfrogs.pixapencil.activities.main.bottomsheet.extendedOnDuplicateTapped
import com.bertandfrogs.pixapencil.activities.main.bottomsheet.extendedOnRenameTapped
import com.bertandfrogs.pixapencil.activities.main.bottomsheet.extendedOnViewDetailsTapped
import com.bertandfrogs.pixapencil.activities.main.oncreate.root.extendedOnCreate
import com.bertandfrogs.pixapencil.activities.main.viewmodel.PixelArtViewModel
import com.bertandfrogs.pixapencil.adapters.PixelArtAdapter
import com.bertandfrogs.pixapencil.databinding.ActivityMainBinding
import com.bertandfrogs.pixapencil.listeners.BottomSheetDialogListener
import com.bertandfrogs.pixapencil.listeners.NewProjectFragmentListener
import com.bertandfrogs.pixapencil.listeners.RecentCreationsListener
import com.bertandfrogs.pixapencil.models.PixelArt


class MainActivity : AppCompatActivity(), RecentCreationsListener, NewProjectFragmentListener, BottomSheetDialogListener {
    lateinit var sharedPreferenceObject: SharedPreferences
    lateinit var adapter: PixelArtAdapter

    lateinit var binding: ActivityMainBinding
    lateinit var menu: Menu

    var showLargeCanvasSizeWarning = true
    var firstLaunch = false
    var darkMode = false
    var mainSpotlight: Spotlight? = null

    val pixelArtViewModel: PixelArtViewModel by viewModels()
    lateinit var pixelArtData: List<PixelArt>

    lateinit var galleryActivityLauncher: ActivityResultLauncher<Array<String>>

    public override fun onCreate(savedInstanceState: Bundle?) {
        initSharedPreferencesObject()
        toggleDarkModeIfApplicable()
        super.onCreate(savedInstanceState)
        extendedOnCreate()
    }

    override fun onCreationTapped(creationTapped: PixelArt) {
        extendedOnCreationTapped(creationTapped)
    }

    override fun onCreationLongTapped(creationTapped: PixelArt) {
        extendedOnCreationLongTapped(creationTapped)
    }

    override fun onStarredTapped(pixelArt: PixelArt) {
        pixelArtViewModel.update(pixelArt)
    }

    override fun onUnstarredTapped(pixelArt: PixelArt) {
        pixelArtViewModel.update(pixelArt)
    }

    override fun onDoneButtonPressed(projectName: String, width: Int, height: Int, spotLightInProgress: Boolean) {
        extendedOnDoneButtonPressed(projectName, width, height, spotLightInProgress)
    }

    override fun onDuplicateTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog) {
        extendedOnDuplicateTapped(pixelArt, bottomSheetDialog)
    }

    override fun onViewDetailsTapped(pixelArt: PixelArt) {
        extendedOnViewDetailsTapped(pixelArt)
    }

    override fun onDeleteTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog) {
        extendedOnDeleteTapped(pixelArt, bottomSheetDialog)
    }

    override fun onRenameTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog) {
        extendedOnRenameTapped(pixelArt, bottomSheetDialog)
    }
}

