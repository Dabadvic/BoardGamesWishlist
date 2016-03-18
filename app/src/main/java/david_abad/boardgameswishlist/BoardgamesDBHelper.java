package david_abad.boardgameswishlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 11/03/2016.
 */
public class BoardgamesDBHelper extends SQLiteOpenHelper {
    public static final String COLUMN_ID = "id";
    public static final String TABLE_NAME = "Boardgames";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_WISH_LEVEL = "wish_level";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_CATEGORIES = "categories";
    public static final String COLUMN_MIN_PLAYERS = "min_players";
    public static final String COLUMN_OWN = "own";

    private static final String[] COLUMNS_BASIC = {COLUMN_ID,COLUMN_NAME,COLUMN_WISH_LEVEL};
    private static final String[] COLUMNS_ALL = {COLUMN_ID,COLUMN_NAME,COLUMN_WISH_LEVEL,
            COLUMN_SCORE,COLUMN_CATEGORIES,COLUMN_MIN_PLAYERS,COLUMN_OWN};

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME +        TEXT_TYPE + "NOT NULL UNIQUE" +     COMMA_SEP +
                    COLUMN_WISH_LEVEL +  INTEGER_TYPE + "NOT NULL" +         COMMA_SEP +
                    COLUMN_SCORE +       REAL_TYPE +                         COMMA_SEP +
                    COLUMN_CATEGORIES +  TEXT_TYPE +                         COMMA_SEP +
                    COLUMN_MIN_PLAYERS + INTEGER_TYPE +                      COMMA_SEP +
                    COLUMN_OWN +         INTEGER_TYPE + //Boolean 0-false 1-true
                    " );";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

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

    /**
     * Adds a new boardgame to the SQLite database.
     *
     * @param game
     * @return
     */
    public boolean addBoardgame(Boardgame game) {
        //for logging
        Log.d("addBook", game.toString());

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, game.getName());
        values.put(COLUMN_WISH_LEVEL, game.getWish_level());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(TABLE_NAME, null, values);
        db.close();

        // If the new row ID is more than 0, that means it has been a successful insert
        return (newRowId >= 0);
    }

    /**
     * Extracts one concrete boardgame from the database.
     *
     * @return
     */
    public Boardgame getBoardgame(int id) {
        // Get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // Build query
        Cursor cursor =
                db.query(TABLE_NAME, // a. table
                        COLUMNS_ALL, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // If we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // Build boardgame object
        Boardgame game = new Boardgame( cursor.getString(0),    // name
                                        cursor.getInt(1),       // wish level
                                (float) cursor.getLong(2),      // rating
                                        cursor.getString(3),    // categories
                                        cursor.getInt(4),       // minimum players
                                        cursor.getInt(5) == 1 ? true : false); // own

        //log
        Log.d("getBoardgame("+id+")", game.toString());

        return game;
    }

    /**
     * Obtains the name of all the boardgames stored in the database.
     *
     * @return
     */
    public List<String> getAllGamesNames()
    {
        List<String> array_list = new ArrayList<String>();

        // Build the query
        String query = "SELECT  * FROM " + TABLE_NAME;

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                array_list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return array_list;
    }

}
