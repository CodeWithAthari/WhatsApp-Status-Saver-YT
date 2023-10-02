package com.devatrii.statussaver.utils

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.net.toUri
import androidx.documentfile.provider.DocumentFile
import com.anggrayudi.storage.file.toRawFile
import com.devatrii.statussaver.R
import com.devatrii.statussaver.models.MediaModel
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


fun Context.isStatusExist(fileName: String): Boolean {
    val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
    val file = File("${downloadDir}/${getString(R.string.app_name)}", fileName)
    return file.exists()
}


fun getFileExtension(fileName: String): String {
    val lastDotIndex = fileName.lastIndexOf(".")

    if (lastDotIndex >= 0 && lastDotIndex < fileName.length - 1) {
        return fileName.substring(lastDotIndex + 1)
    }
    return ""
}

private const val TAG = "FileUtils"
fun Context.saveStatus(model: MediaModel): Boolean {
    if (isStatusExist(model.fileName)) {
        return true
    }
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
        Log.d(TAG, "saveStatus: Saving in Android P or Less")
        return saveStatusBeforeQ(this, Uri.parse(model.pathUri))
    }
    val extension = getFileExtension(model.fileName)
    val mimeType = "${model.type}/$extension"
    val inputStream = contentResolver.openInputStream(model.pathUri.toUri())
    try {
        val values = ContentValues()
        values.apply {
            put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
            put(MediaStore.MediaColumns.DISPLAY_NAME, model.fileName)
            put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_DOCUMENTS + "/" + getString(R.string.app_name)
            )
        }
        Log.d(TAG, "saveStatus: File URI: ${model.pathUri}")
        Log.d(
            TAG,
            "saveStatus: External Content URI: ${MediaStore.Files.getContentUri("external")}"
        )
        Log.d(TAG, "saveStatus: Values $values")
        val uri = contentResolver.insert(
            MediaStore.Files.getContentUri("external"),
            values
        )
        uri?.let {
            val outputStream = contentResolver.openOutputStream(it)
            if (inputStream != null) {
                outputStream?.write(inputStream.readBytes())
            }
            outputStream?.close()
            inputStream?.close()
            return true
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}


fun saveStatusBeforeQ(context: Context, uri: Uri): Boolean {
    try {
        val sourceFile = DocumentFile.fromTreeUri(context, uri)?.toRawFile(context)?.takeIf { f2 ->
            f2.canRead()
        }
        val distFile =
            sourceFile?.name?.let {
                File(
                    "${Environment.getExternalStorageDirectory()}/Documents/${context.getString(R.string.app_name)}",
                    it
                )
            }
        distFile?.let {
            if (!distFile.parentFile?.exists()!!) {
                distFile.parentFile?.mkdirs()
            }
            if (!distFile.exists()) {
                distFile.createNewFile()
            }
            val source = FileInputStream(sourceFile).channel
            val destination = FileOutputStream(distFile).channel
            destination.transferFrom(source, 0, source.size())
            source.close()
            destination.close()
            return true
        }

        return false
    } catch (e: Exception) {
        return false
    }

}








