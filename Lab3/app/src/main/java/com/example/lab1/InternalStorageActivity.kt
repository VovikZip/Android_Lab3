package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab1.fragments.InternalStorageFragment
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class InternalStorageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal_storage)

        val data: String = readInternalStorage("myData")
        val internalStorageFragment = InternalStorageFragment()
        val bundle = Bundle()

        bundle.putString("storage", data)
        internalStorageFragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.storage_fragment_container, internalStorageFragment)
            .commit()
    }

    private fun readInternalStorage(fileName: String): String{
        val fileInputStream: FileInputStream? = openFileInput(fileName)
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String?
        while (run {
                text = bufferedReader.readLine()
                text
            } != null) {
            stringBuilder.append(text)
        }
        return stringBuilder.toString()
    }
}