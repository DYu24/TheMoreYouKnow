package com.derekyu624gmail.themoreyouknow

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

open class SQLiteAssetHelper(context: Context, name: String, factory: SQLiteDatabase.CursorFactory, version: Int)

class DatabaseOpenHelper : SQLiteAssetHelper {
    companion object {
        private val DATABASE_NAME: String = "facts.db"
        private val DATABASE_VERSION: Int = 1
    }
    constructor(context: Context) : super(context, DATABASE_NAME, null, DATABASE_VERSION)
}