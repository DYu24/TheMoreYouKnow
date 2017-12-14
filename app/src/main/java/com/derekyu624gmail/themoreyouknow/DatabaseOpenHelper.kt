package com.derekyu624gmail.themoreyouknow

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DatabaseOpenHelper : SQLiteAssetHelper {
    constructor(context: Context) : super(context, "facts.db", null, 1)
}