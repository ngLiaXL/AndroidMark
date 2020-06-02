package com.example.androidmark.security

import android.content.SharedPreferences
import android.os.Bundle
import androidx.security.crypto.EncryptedSharedPreferences
import com.example.androidmark.BaseActivity
import com.example.androidmark.R

class SecurityActivity : BaseActivity() {

    private val fileName: String = "file-security"
    private val masterKeyAlias: String = "master-key-alias"
    private lateinit var sharedPrefsEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security)

        initPreferences()


        sharedPrefsEditor.putBoolean("one", true);
        sharedPrefsEditor.putString("two", "test")

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

    }
}