package david_abad.boardgameswishlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by david on 11/03/2016.
 */
public class BoardgamesDBHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BoardgameEntry.TABLE_NAME + " (" +
                    BoardgameEntry._ID + " INTEGER PRIMARY KEY," +
                    BoardgameEntry.COLUMN_NAME +        TEXT_TYPE +     COMMA_SEP +
                    BoardgameEntry.COLUMN_WISH_LEVEL +  INTEGER_TYPE +  COMMA_SEP +
                    BoardgameEntry.COLUMN_SCORE +       REAL_TYPE +     COMMA_SEP +
                    BoardgameEntry.COLUMN_CATEGORIES +  TEXT_TYPE +     COMMA_SEP +
                    BoardgameEntry.COLUMN_MIN_PLAYERS + INTEGER_TYPE +  COMMA_SEP +
                    BoardgameEntry.COLUMN_OWN +         INTEGER_TYPE +  COMMA_SEP + //Booleano 0-false 1-true
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BoardgameEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Boardgames.db";

    public BoardgamesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public static abstract class BoardgameEntry implements BaseColumns {
        public static final String TABLE_NAME = "Boardgames";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_WISH_LEVEL = "wish_level";
        public static final String COLUMN_SCORE = "score";
        public static final String COLUMN_CATEGORIES = "categories";
        public static final String COLUMN_MIN_PLAYERS = "min_players";
        public static final String COLUMN_OWN = "own";

    }
}
