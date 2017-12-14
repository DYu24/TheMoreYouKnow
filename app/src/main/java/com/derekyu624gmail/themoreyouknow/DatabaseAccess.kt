package com.derekyu624gmail.themoreyouknow

import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract

import java.util.ArrayList
import java.util.List
import java.util.Random

class DatabaseAccess {
    //properties
    private var openHelper: DatabaseOpenHelper
    private lateinit var database: SQLiteDatabase

    //creating a "static" field
    companion object {
        lateinit var instance: DatabaseAccess
        fun getInstance(context: Context): DatabaseAccess {
            if (DatabaseAccess.instance == null) {
                DatabaseAccess.instance = DatabaseAccess(context)
            }
            return DatabaseAccess.instance
        }
    }

    constructor(context: Context) {
        this.openHelper = DatabaseOpenHelper(context)
    }

    fun open() {
        this.database = openHelper.getWritableDatabase()
    }

    fun close() {
        if (database != null) {
            this.database.close()
        }
    }

    fun getFact(table: String): String {
        var id: Int = generateRandomID()
        var cursor: Cursor = database.rawQuery("SELECT * FROM " + table + " WHERE fact_id=" + id, null)
        var fact: String = cursor.getString(cursor.getColumnIndex("fact_desc"))
        cursor.close()
        return fact
    }

    private fun generateRandomID(): Int {
        val random = Random()
        val db: SQLiteDatabase = openHelper.readableDatabase
        var numEntries: Long = DatabaseUtils.queryNumEntries(db, "space")
        return random.nextInt(numEntries.toInt()-1) + numEntries.toInt() + 1
    }
}

