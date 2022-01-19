package com.example.lab1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lab1.fragments.InputFragment
import com.example.lab1.fragments.OutputFragment
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), InputFragment.OnTextSent, InputFragment.ShowStorage {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputFragment = InputFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, inputFragment)
            .commit()
    }

    override fun sendData(
        editTextInput: String,
        sansSerifButtonChecked: Boolean,
        monospaceButtonChecked: Boolean,
        serifButtonChecked: Boolean
    ) {
        val outputFragment = OutputFragment()
        val bundle = Bundle()

        if (editTextInput.isNotEmpty()) {
            bundle.putString("editTextInput", editTextInput)
            bundle.putBoolean("sansSerifButtonChecked", sansSerifButtonChecked)
            bundle.putBoolean("monospaceButtonChecked", monospaceButtonChecked)
            bundle.putBoolean("serifButtonChecked", serifButtonChecked)

            outputFragment.arguments = bundle

            writeFileOnInternalStorage("myData", editTextInput)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, outputFragment).addToBackStack(null).commit()

        } else {
            Toast.makeText(applicationContext, "Input field empty!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun show() {
        val intent = Intent(this, InternalStorageActivity::class.java)
        startActivity(intent)
    }

    private fun writeFileOnInternalStorage(fileName: String, fileData: String) {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND)
            fileOutputStream.write(("$fileData, ").toByteArray())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}
