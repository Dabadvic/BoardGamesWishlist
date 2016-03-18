package david_abad.boardgameswishlist;

import java.util.Arrays;
import java.util.List;

/**
 * Created by david on 18/03/2016.
 */
public class Boardgame {
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

        // Extract the categories
        categories = Arrays.asList(_categories.split(","));
    }

}
