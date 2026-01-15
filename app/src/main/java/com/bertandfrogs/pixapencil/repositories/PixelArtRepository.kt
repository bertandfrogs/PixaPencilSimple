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

package com.bertandfrogs.pixapencil.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.bertandfrogs.pixapencil.dao.PixelArtCreationsDao
import com.bertandfrogs.pixapencil.database.AppData
import com.bertandfrogs.pixapencil.database.PixelArtDatabase
import com.bertandfrogs.pixapencil.models.PixelArt
import kotlinx.coroutines.*

class PixelArtRepository(application: Application) {
    init {
        AppData.pixelArtDB = PixelArtDatabase.get(application)
    }

    private val dao: PixelArtCreationsDao = AppData.pixelArtDB.dao()
    private val allCreations: LiveData<List<PixelArt>> = AppData.pixelArtDB.dao().getAll()

    fun insert(pixelArt: PixelArt) {
        InsertAsync(dao).execute(pixelArt)
    }

    fun update(pixelArt: PixelArt) {
        UpdateAsync(dao).execute(pixelArt)
    }

    fun delete(pixelArt: PixelArt) {
        DeleteAsync(dao).execute(pixelArt)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

    fun getAll(): LiveData<List<PixelArt>> {
        return allCreations
    }

    private class InsertAsync(private val dao: PixelArtCreationsDao) : CoroutineScope by MainScope() {
        fun execute(pixelArt: PixelArt) {
            CoroutineScope(Dispatchers.IO).launch {
                dao.insert(pixelArt)
            }
        }
    }

    private class UpdateAsync(private val dao: PixelArtCreationsDao) : CoroutineScope by MainScope() {
        fun execute(pixelArt: PixelArt) {
            CoroutineScope(Dispatchers.IO).launch {
                dao.update(pixelArt)
            }
        }
    }

    private class DeleteAsync(private val dao: PixelArtCreationsDao) : CoroutineScope by MainScope() {
        fun execute(pixelArt: PixelArt) {
            CoroutineScope(Dispatchers.IO).launch {
                dao.delete(pixelArt)
            }
        }
    }
}