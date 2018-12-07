package com.develop.daniil.moviefeed_v02.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import android.util.Log

import java.util.ArrayList

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_NEWS_TABLE)
        db.execSQL(CREATE_REVIEWS_TABLE)
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_NEWS)
        db.execSQL(SQL_DELETE_REVIEWS)
        db.execSQL(SQL_DELETE_USER)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun readAll(){
        val db = this.writableDatabase
        Log.e("Debug",(db.rawQuery("SELECT * FROM "+ TABLE_USER, null)).toString())
    }

    fun checkAuth(login:String){
        val db = this.readableDatabase
        val c:Cursor =  db.rawQuery("SELECT * FROM "+ TABLE_USER + " WHERE "+ COLUMN_LOGIN +" = "+login , null)

        if(c.getCount() == 0){
            //TODO: Сделать обработку неавторизации
        }
    }


    @Throws(SQLiteConstraintException::class)
    fun addRecToUserTable(login:String,pass:String,email: String,root:Int):Boolean{
        val db = writableDatabase

        val values = ContentValues()

        values.put(COLUMN_LOGIN, login)
        values.put(COLUMN_PASS, pass)
        values.put(COLUMN_EMAIL, email)
        values.put(COLUMN_ROOT, root)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(TABLE_USER, null, values)

        return true

    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DB_NAME = "MovieFeed"

        val TABLE_NEWS = "tblNews"
        val COLUMN_ID = "_id"
        val COLUMN_REF = "ref"
        val COLUMN_TITLE = "title"
        val COLUMN_TEXT = "text"
        val COLUMN_PICTURE = "picture"
        val COLUMN_DATE = "date"
        val COLUMN_SOURCE = "source"
        val COLUMN_NEWSID = "newsid"

        val TABLE_REVIEWS = "tblReviews"
        val COLUMN_STATUS = "status"
        val COLUMN_AUTHOR = "author"

        val TABLE_USER = "tblUser"
        val COLUMN_LOGIN = "login"
        val COLUMN_EMAIL = "email"
        val COLUMN_ROOT = "root"
        val COLUMN_PASS = "password"

        val CREATE_NEWS_TABLE = ("CREATE TABLE " +
                TABLE_NEWS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NEWSID + " INTEGER,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_TEXT + " TEXT,"
                + COLUMN_PICTURE + " TEXT,"
                + COLUMN_SOURCE + " TEXT,"
                + COLUMN_REF + " TEXT,"
                + COLUMN_DATE + " DATE"+ ")")

        val CREATE_REVIEWS_TABLE = ("CREATE TABLE " +
                TABLE_REVIEWS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_TEXT + " TEXT,"
                + COLUMN_PICTURE + " TEXT,"
                + COLUMN_SOURCE + " TEXT,"
                + COLUMN_REF + " TEXT,"
                + COLUMN_DATE + " DATE,"
                + COLUMN_AUTHOR + " TEXT,"
                + COLUMN_STATUS + " INTEGER"
                + ")")

        val CREATE_USER_TABLE = ("CREATE TABLE " +
                TABLE_USER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_LOGIN + " TEXT,"
                + COLUMN_PASS + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_ROOT + " INTEGER"
                + ")")

        private val SQL_DELETE_NEWS = "DROP TABLE IF EXISTS " + TABLE_NEWS
        private val SQL_DELETE_REVIEWS = "DROP TABLE IF EXISTS " + TABLE_REVIEWS
        private val SQL_DELETE_USER = "DROP TABLE IF EXISTS " + TABLE_USER
    }

}
