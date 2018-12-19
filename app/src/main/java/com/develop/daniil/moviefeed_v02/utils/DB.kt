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
import com.develop.daniil.moviefeed_v02.RequestsClasses.Sources
import java.net.URL

import java.util.ArrayList

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DATABASE_VERSION) {

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

        val TABLE_SOURCES = "tblSources"
        val COLUMN_IDSOURCES = "idSources"
        val COLUMN_NAME = "name"

        val CREATE_SOURCES_TABLE = ("CREATE TABLE " +
                TABLE_SOURCES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_IDSOURCES + " INTEGER,"
                + COLUMN_NAME + " TEXT " + ")")

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
        private val SQL_DELETE_SOURCES = "DROP TABLE IF EXISTS " + TABLE_SOURCES
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_NEWS_TABLE)
        db.execSQL(CREATE_REVIEWS_TABLE)
        db.execSQL(CREATE_USER_TABLE)
        db.execSQL(CREATE_SOURCES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_NEWS)
        db.execSQL(SQL_DELETE_REVIEWS)
        db.execSQL(SQL_DELETE_USER)
        db.execSQL(SQL_DELETE_SOURCES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun readAll(){
        val db = this.writableDatabase
        val cur = db.rawQuery("SELECT * FROM "+ TABLE_USER, null)

        cur.moveToFirst()

        val t = cur.getCount()

        for (i: Int in 0..t) {
            Log.e("Debug", cur.getString(cur.getColumnIndex(COLUMN_LOGIN)))
        }
            // Log.e("Debug",cur.get)
    }

    fun getUserInfo():Int{
        val db = this.readableDatabase
        val c:Cursor =  db.rawQuery("SELECT * FROM "+ TABLE_USER, null)

        if(c.getCount() == 0){
            return 1
        }

        c.moveToFirst();

        val name = c
            .getString(c
                .getColumnIndex(COLUMN_LOGIN));

        return 0
    }

    fun getUserData():Array<String>{

        val db = this.readableDatabase
        val c:Cursor =  db.rawQuery("SELECT * FROM "+ TABLE_USER, null)

        c.moveToFirst();

        val name = c
            .getString(c
                .getColumnIndex(COLUMN_LOGIN));

        val email =  c
            .getString(c
                .getColumnIndex(COLUMN_EMAIL));

        val user:Array<String> = arrayOf(name,email)

        return user
    }

    fun clearTable(tableName:String){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM "+ tableName)
        db.execSQL("vacuum")
    }

    @Throws(SQLiteConstraintException::class)
    fun setSources(sources: Array<Sources>):Boolean{
        val db = this.writableDatabase
        try {
            clearTable(TABLE_SOURCES)
        }catch (e: Exception){
            Log.e("Debug:", e.toString())
        }

        for (source in sources.orEmpty()){
            val id = source.source_id
            val name  = source.name

            val values = ContentValues()

            values.put(COLUMN_IDSOURCES, id)
            values.put(COLUMN_NAME, name)
            val newRow = db.insert(TABLE_SOURCES, null, values)
        }

        return true
    }

    fun getSource(id:Int):String{
        val db = this.readableDatabase
        val c:Cursor =  db.rawQuery("SELECT name FROM "+ TABLE_SOURCES + " WHERE " + COLUMN_IDSOURCES + " = " + id.toString(), null)

        if(c.getCount() == 0){
            return "null"
        }

        c.moveToFirst();

        val name = c
            .getString(c
                .getColumnIndex(COLUMN_NAME));

        return name
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



}
