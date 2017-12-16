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
    private var openHelper: SQLiteOpenHelper? = null
    private var database: SQLiteDatabase? = null

    //creating a "static" field
    companion object {
        private var instance: DatabaseAccess? = null
        fun getInstance(context: Context): DatabaseAccess? {
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
        this.database = openHelper!!.writableDatabase
    }

    fun close() {
        if (database != null) {
            this.database?.close()
        }
    }

    fun getFact(table: String): String {
        val id: Int = generateRandomID(table)
        val cursor: Cursor = database!!.rawQuery("SELECT * FROM " + table, null)
        cursor.moveToPosition(id)
        val fact: String = cursor.getString(cursor.getColumnIndex("fact_desc"))
        cursor.close()
        return fact
    }

    private fun generateRandomID(table: String): Int {
        val random = Random()
        val db: SQLiteDatabase = openHelper!!.readableDatabase
        val numEntries: Long = DatabaseUtils.queryNumEntries(db, table)
        return random.nextInt(numEntries.toInt()-1) + 1
    }
}

