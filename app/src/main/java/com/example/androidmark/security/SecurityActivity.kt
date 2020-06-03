package com.example.androidmark.security

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.androidmark.BaseActivity
import com.example.androidmark.R
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class SecurityActivity : BaseActivity() {

    private val fileName: String = "file-security"
    private val masterKeyAlias: String = "master-key-alias"
    private lateinit var sharedPrefsEditor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security)

        initPreferences()



        Log.d("OWEN", "filesDir : $filesDir")
        Log.d(
            "OWEN",
            "getExternalFilesDir : ${getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)}"
        )
        Log.d("OWEN", "cacheDir: $cacheDir")
        Log.d("OWEN", "externalCacheDir: $externalCacheDir")


    }


    fun onClickWrite(view: View) {
        write()
    }

    fun onClickRead(view: View) {

        read()
    }

    private fun read() {
        // Although you can define your own key generation parameter specification, it's
        // recommended that you use the value specified here.
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        val fileToRead = "my_sensitive_data.txt"
        val encryptedFile = EncryptedFile.Builder(
            File(filesDir, fileToRead),
            this,
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()


        val openFileInput = encryptedFile.openFileInput()
        val b  = ByteArray(1024)
        val read = openFileInput.read(b)

        Log.d("OWEN", "read: ${String(b, 0, read)}")

    }


    private fun initPreferences() {
        val sharedPreferences = EncryptedSharedPreferences
            .create(
                fileName,
                masterKeyAlias,
                this,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )

        sharedPrefsEditor = sharedPreferences.edit()


        sharedPrefsEditor.putBoolean("one", true)
        sharedPrefsEditor.putString("two", "test").apply()

    }


    private fun write() {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)


        // Creates a file with this name, or replaces an existing file
        // that has the same name. Note that the file name cannot contain
        // path separators.
        val fileToWrite = "my_sensitive_data.txt"

        val file = File(filesDir, fileToWrite)

        val encryptedFile = EncryptedFile.Builder(
            file,
            this,
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        encryptedFile.openFileOutput().bufferedWriter().use {
            it.write("MY SUPER-SECRET INFORMATION")
            it.close()
        }
    }

}