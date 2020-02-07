package com.example.relayapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.toBitmap
import com.example.relayapplication.service.model.SelectApplicationInfo
import com.example.relayapplication.service.repository.SelectApp
import com.example.relayapplication.service.repository.SelectAppRealm


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)

        val realm = Realm.getDefaultInstance()
        val all = realm.where(SelectAppRealm::class.java).findAll()

        val appList = ArrayList<SelectApplicationInfo>()

        for (items in all) {

            val opt = BitmapFactory.Options()
            opt.inJustDecodeBounds = false
            val bitmap =
                items.appIcon?.size?.let {
                    BitmapFactory.decodeByteArray(items.appIcon, 0,
                        it, opt)
                }

            val image = BitmapDrawable(resources, bitmap)

            appList.add(
                SelectApplicationInfo(
                    items.appName,
                    image,
                    items.entryName,
                    items.appPackage,
                    items.appClass
                )
            )
        }

        SelectApp.selectAppList = appList

        val navController = findNavController(R.id.navigationView)
        NavigationUI.setupWithNavController(bottom_navigation_view, navController)
    }

    /**
     * バックキー押した際の処理
     */
    override fun onBackPressed() {
        super.onBackPressed()

        Log.d("check", "onBackPressed")

        finish()
    }

    override fun onPause() {
        super.onPause()

        Log.d("check", "onPause")
        val realm = Realm.getDefaultInstance()
        val allItem = realm.where(SelectAppRealm::class.java).findAll()

        realm.executeTransaction{realm ->
            allItem.deleteAllFromRealm()

            for (items in SelectApp.selectAppList) {
                Log.d("check", items.appIcon.toString())
                val bitmap = items.appIcon.toBitmap()
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val bitmapdata = stream.toByteArray()

                Log.d("checkItems", items.toString())
                val obj = realm.createObject(SelectAppRealm::class.java, items.appName)
                obj.appIcon = bitmapdata
                obj.entryName = items.entryAppName
                obj.appClass = items.packageClass
                obj.appPackage = items.packageName

            }
        }
        realm.close()
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("check", "onDestroy")
    }
}
