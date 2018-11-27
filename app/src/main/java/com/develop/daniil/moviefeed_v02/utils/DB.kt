package com.develop.daniil.moviefeed_v02.utils

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues

class MyDBHandler(context: Context, name: String?,
                  factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    ///ПЕРЕДЕЛАТЬ!!!!!!!
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_NEWS_TABLE = ("CREATE TABLE " +
                TABLE_NEWS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PRODUCTNAME
                + " TEXT," + COLUMN_QUANTITY + " INTEGER" + ")")
        db.execSQL(CREATE_NEWS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {

    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "MovieFeed"
        val TABLE_NEWS = "News"
        val TABLE_REVIEWS = "Reviews"
        val TABLE_USER = "User"

        val COLUMN_ID = "_id"
        val COLUMN_PRODUCTNAME = "productname"
        val COLUMN_QUANTITY = "quantity"
    }
}