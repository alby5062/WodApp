package it.alberto.wodapp.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper


class DatabaseHelper(private val context: Context?) : SQLiteAssetHelper(context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION) {

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_1")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_2")
        onCreate(db)
    }

    fun add(name: String?, type: String?, date: String, exercises: String, result_wod: String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_NAME, name)
        cv.put(COLUMN_TYPE, type)
        cv.put(COLUMN_DATE, date)
        cv.put(COLUMN_EX, exercises)
        cv.put(COLUMN_RESULT_WOD, result_wod)


        val result = db.insert(TABLE_NAME_1, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    fun readUserData(my_date: String): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME_1 WHERE date LIKE '%$my_date%'"
        val db = this.readableDatabase
        var cursor_user: Cursor? = null
        if (db != null) {
            cursor_user = db.rawQuery(query, null)
        }
        return cursor_user
    }

    fun readBaseData(): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME_2"
        val db = this.readableDatabase
        var cursor_base: Cursor? = null
        if (db != null) {
            cursor_base= db.rawQuery(query, null)
        }
        return cursor_base
    }

    fun updateData(row_id: String, name: String?, type: String?, date: String?, result_wod: String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_NAME, name)
        cv.put(COLUMN_TYPE, type)
        cv.put(COLUMN_DATE, date)
        cv.put(COLUMN_RESULT_WOD, result_wod)
        val result = db.update(TABLE_NAME_1, cv, "_id=?", arrayOf(row_id)).toLong()
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteOneRow(row_id: String) {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME_1, "_id=?", arrayOf(row_id)).toLong()
        if (result == -1L) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteAllData() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME_1")
    }

    fun takeLastUpload(): Cursor?{
        val db = this.readableDatabase
        val query = "SELECT * from $TABLE_NAME_1 order by _id DESC limit 1"
        var cursor_last: Cursor? = null
        if(db != null) {
            cursor_last = db.rawQuery(query, null)
        }
        return cursor_last
    }

    fun takeCountWod(my_date: String): Int{
        val db = this.readableDatabase
        val query = "SELECT COUNT(*) FROM $TABLE_NAME_1 WHERE date LIKE '%$my_date%'"
        return DatabaseUtils.longForQuery(db, query, null).toInt()
    }

    fun deleteOnlyDate(my_date: String?){
        val db = this.writableDatabase
        val query = "DELETE FROM $TABLE_NAME_1 WHERE date LIKE '%$my_date%' "
        db.execSQL(query)
    }

    companion object {
        private const val DATABASE_NAME = "wod_database.db"
        private const val DATABASE_VERSION = 3
        private const val TABLE_NAME_1 = "user_wod"
        private const val TABLE_NAME_2 = "base_wod"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_TYPE = "type"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_EX = "exercises"
        private const val COLUMN_RESULT_WOD = "result"
    }
}




