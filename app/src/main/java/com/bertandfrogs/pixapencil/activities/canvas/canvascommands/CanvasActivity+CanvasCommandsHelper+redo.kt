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

package com.bertandfrogs.pixapencil.activities.canvas.canvascommands

import com.bertandfrogs.pixapencil.R
import com.bertandfrogs.pixapencil.activities.canvas.CanvasActivity
import com.bertandfrogs.pixapencil.activities.canvas.onactioncompleted.onRedoActionCompleted
import com.bertandfrogs.pixapencil.extensions.doAddLast
import com.bertandfrogs.pixapencil.extensions.enable
import com.bertandfrogs.pixapencil.extensions.setPixel

fun CanvasActivity.CanvasCommandsHelper.redo() {
    if (baseReference.viewModel.redoStack.size > 0) {
        baseReference.menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()

        for (obj in baseReference.viewModel.redoStack.last().actionData.distinctBy {
            it.coordinates
        }) {
            baseReference.binding.activityCanvasPixelGridView.pixelGridViewBitmap.setPixel(obj.coordinates, obj.colorSet)
        }

        baseReference.binding.activityCanvasPixelGridView.invalidate()

        baseReference.viewModel.undoStack.doAddLast(baseReference.viewModel.redoStack.last())
        baseReference.viewModel.redoStack.removeLast()

        baseReference.onRedoActionCompleted(baseReference.viewModel.redoStack)
    }
}