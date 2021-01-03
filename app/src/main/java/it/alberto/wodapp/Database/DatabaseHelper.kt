package it.alberto.wodapp.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


internal class DatabaseHelper(private val context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_DATE + " INTEGER);"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun add(name: String?, type: String?, date: String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_NAME, name)
        cv.put(COLUMN_TYPE, type)
        cv.put(COLUMN_DATE, date)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    fun readAllData(): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        if (db != null) {
            cursor = db.rawQuery(query, null)
        }
        return cursor
    }

    fun updateData(row_id: String, name: String?, type: String?, date: String?) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_NAME, name)
        cv.put(COLUMN_TYPE, type)
        cv.put(COLUMN_DATE, date)
        val result = db.update(TABLE_NAME, cv, "_id=?", arrayOf(row_id)).toLong()
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteOneRow(row_id: String) {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "_id=?", arrayOf(row_id)).toLong()
        if (result == -1L) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteAllData() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }

    companion object {
        private const val DATABASE_NAME = "wod_database.db"
        private const val DATABASE_VERSION = 2
        private const val TABLE_NAME = "user_wod"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_TYPE = "type"
        private const val COLUMN_DATE = "date"
    }
}




