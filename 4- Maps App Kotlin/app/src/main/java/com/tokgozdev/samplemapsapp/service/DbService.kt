package com.tokgozdev.samplemapsapp.service

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.tokgozdev.samplemapsapp.model.Location

class DbService(
        val context: Context,

        ) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val TABLE_NAME = "Location"
    private val NAME = "name"
    private val COUNTRY = "country"
    private val TYPE = "type"
    private val LATITUDE = "latitude"
    private val LONGITUDE = "longitude"

    companion object {
        private val DATABASE_NAME = "SQLITE_DATABASE"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE $TABLE_NAME " +
                "($NAME VARCHAR (256)," +
                "$COUNTRY VARCHAR(256)," +
                "$TYPE VARCHAR(256)," +
                "$LATITUDE REAL," +
                "$LONGITUDE REAL)"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(location: Location) {
        val sqliteDb = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(NAME, location.name)
        contentValues.put(COUNTRY, location.country)
        contentValues.put(TYPE, location.type)
        contentValues.put(LATITUDE, location.latitude)
        contentValues.put(LONGITUDE, location.longitude)

        val result = sqliteDb.insert(TABLE_NAME, null, contentValues)

        Toast.makeText(context, if (result != -1L) "Success"
        else "Try Again.", Toast.LENGTH_LONG).show()
    }

    fun readData(): ArrayList<Location> {
        val locationList = arrayListOf<Location>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                val name = result.getString(result.getColumnIndex(NAME)).toString()
                val country = result.getString(result.getColumnIndex(COUNTRY)).toString()
                val type = result.getString(result.getColumnIndex(TYPE)).toString()
                val latitude = result.getString(result.getColumnIndex(LATITUDE)).toDouble()
                val longitude = result.getString(result.getColumnIndex(LONGITUDE)).toDouble()

                locationList.add(Location(name, country, type, latitude, longitude))
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return locationList
    }
}