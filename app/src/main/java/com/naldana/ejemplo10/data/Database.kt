import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${DatabaseContract.MonedaEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${DatabaseContract.MonedaEntry.COLUMN_NAME} TEXT," +
            "${DatabaseContract.MonedaEntry.COLUMN_COUNTRY} TEXT," +
            "${DatabaseContract.MonedaEntry.COLUMN_VALUE} INTEGER)" +
            "${DatabaseContract.MonedaEntry.COLUMN_VALUE_US} INTEGER," +
            "${DatabaseContract.MonedaEntry.COLUMN_YEAR} INTEGER," +
            "${DatabaseContract.MonedaEntry.COLUMN_REVIEW} TEXT," +
            "${DatabaseContract.MonedaEntry.COLUMN_ISAVAILABLE} INTEGER," +
            "${DatabaseContract.MonedaEntry.COLUMN_IMAGE} TEXT)"


private const val SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS ${DatabaseContract.MonedaEntry.TABLE_NAME}"

class Database(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    companion object {
        const val DATABASE_NAME = "moneycache.db"
        const val DATABASE_VERSION = 1
    }
}