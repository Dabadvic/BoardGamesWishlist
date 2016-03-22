package david_abad.boardgameswishlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class BoardGameDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_game_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int id = (int) intent.getLongExtra(MainScreen.EXTRA_ID, -1);

        BoardgamesDBHelper db = new BoardgamesDBHelper(this);
        Boardgame game = db.getBoardgame(id);

        TextView textViewGame = (TextView) findViewById(R.id.text_board_game_name);
        textViewGame.setText(game.getName());

        RatingBar wish_level_bar = (RatingBar) findViewById(R.id.rating_wish_details);
        wish_level_bar.setNumStars(game.getWish_level());
    }

}
