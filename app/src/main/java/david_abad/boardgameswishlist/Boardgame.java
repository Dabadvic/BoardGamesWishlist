package david_abad.boardgameswishlist;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;
import java.util.List;

/**
 * Created by david on 18/03/2016.
 */
public class Boardgame {
    private int id; //ID for the database
    private String name;
    private int wish_level;
    private float score;
    private List<String> categories;
    private int min_players;
    private boolean own;

    /**
     * Constructor with the basic info necessary for creating a new boardgame.
     *
     * @param _name
     * @param _wish_level
     */
    public Boardgame(String _name, int _wish_level) {
        name = _name;
        wish_level = _wish_level;
        score = 0;
        categories = null;
        min_players = 0;
        own = false;
    }

    /**
     * Constructor providing all the information for a boardgame.
     *
     * @param _name
     * @param _wish_level
     * @param _score
     * @param _categories
     * @param _min_players
     */
    public Boardgame(String _name, int _wish_level, float _score, String _categories, int _min_players) {
        name = _name;
        wish_level = _wish_level;
        score = _score;
        min_players = _min_players;
        own = false;

        // Extract the categories
        categories = Arrays.asList(_categories.split(","));

    }

    /**
     * Complete constructor providing all the information for a boardgame.
     *
     * @param _name
     * @param _wish_level
     * @param _score
     * @param _categories
     * @param _min_players
     * @param _own
     */
    public Boardgame(String _name, int _wish_level, float _score, String _categories, int _min_players, boolean _own) {
        name = _name;
        wish_level = _wish_level;
        score = _score;
        min_players = _min_players;
        own = _own;

        // Extract the categories if not null
        if (_categories != null) {
            categories = Arrays.asList(_categories.split(","));
        } else {
            categories = null;
        }
    }

    /**
     * Constructor with the basic info necessary for creating a new boardgame, plus ID.
     *
     * @param _id
     * @param _name
     * @param _wish_level
     */
    public Boardgame(int _id, String _name, int _wish_level) {
        id = _id;
        name = _name;
        wish_level = _wish_level;
        score = 0;
        categories = null;
        min_players = 0;
        own = false;
    }

    /**
     * Complete constructor providing all the information for a boardgame, plus ID.
     *
     * @param _name
     * @param _wish_level
     * @param _score
     * @param _categories
     * @param _min_players
     * @param _own
     */
    public Boardgame(int _id, String _name, int _wish_level, float _score, String _categories, int _min_players, boolean _own) {
        id = _id;
        name = _name;
        wish_level = _wish_level;
        score = _score;
        min_players = _min_players;
        own = _own;

        // Extract the categories if not null
        if (_categories != null) {
            categories = Arrays.asList(_categories.split(","));
        } else {
            categories = null;
        }
    }


    public String getName() {
        return name;
    }

    public int getWish_level() {
        return wish_level;
    }

    public float getScore() {
        return score;
    }

    public List<String> getCategories() {
        return categories;
    }

    public int getMin_players() {
        return min_players;
    }

    public boolean isOwn() {
        return own;
    }

    public String getCategoriesLine() {
        String line = "";
        for (String category : categories)
            line = line + ", " + category;

        return line;
    }

    /**
     * Returns a string with the main information of the boardgame.
     *
     * @return String with name and wish level.
     */
    public String toString() {
        String line;
        line = "Name: " + name + ", wish: " + wish_level;

        return line;
    }

    public int getId() {
        return id;
    }
}
