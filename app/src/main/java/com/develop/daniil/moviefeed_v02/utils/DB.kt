package com.develop.daniil.moviefeed_v02.utils

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues

class MyDBHandler(context: Context, name: String?,
                  factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_NEWS_TABLE = ("CREATE TABLE " +
                TABLE_NEWS + "("
                + COLUMN_ID
                + COLUMN_REF
                + COLUMN_TITLE
                + COLUMN_TEXT
                + COLUMN_PICTURE
                + COLUMN_DATE
                + COLUMN_SOURCE + ")")

        val CREATE_REVIEWS_TABLE = ("CREATE TABLE " +
                TABLE_NEWS + "("
                + COLUMN_ID
                + COLUMN_REF
                + COLUMN_TITLE
                + COLUMN_TEXT
                + COLUMN_PICTURE
                + COLUMN_DATE
                + COLUMN_SOURCE
                + COLUMN_AUTHOR
                + COLUMN_STATUS + ")")

        val CREATE_USER_TABLE = ("CREATE TABLE " +
                TABLE_NEWS + "("
                + COLUMN_ID
                + COLUMN_LOGIN
                + COLUMN_PASS
                + COLUMN_EMAIL
                + COLUMN_ROOT
                 + ")")
        db.execSQL(CREATE_NEWS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {

    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "MovieFeed"

        val TABLE_NEWS = "tblNews"
        val COLUMN_ID = "_id INTEGER PRIMARY KEY,"
        val COLUMN_REF = "ref STRING,"
        val COLUMN_TITLE = "title STRING,"
        val COLUMN_TEXT = "text STRING,"
        val COLUMN_PICTURE = "picture STRING,"
        val COLUMN_DATE = "date DATE,"
        val COLUMN_SOURCE = "source STRING"

        val TABLE_REVIEWS = "tblReviews"
        val COLUMN_STATUS = "status INTEGER,"
        val COLUMN_AUTHOR = "author STRING,"

        val TABLE_USER = "tblUser"
        val COLUMN_LOGIN = "login STRING,"
        val COLUMN_EMAIL = "email STRING,"
        val COLUMN_ROOT = "root INTEGER,"
        val COLUMN_PASS = "password STRING,"


    }
}