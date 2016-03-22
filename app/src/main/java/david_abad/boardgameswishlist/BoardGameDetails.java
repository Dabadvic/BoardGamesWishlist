package david_abad.boardgameswishlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class BoardGameDetails extends AppCompatActivity {
    int game_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_game_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        game_id = (int) intent.getLongExtra(MainScreen.EXTRA_ID, -1);

        BoardgamesDBHelper db = new BoardgamesDBHelper(this);
        Boardgame game = db.getBoardgame(game_id);

        TextView textViewGame = (TextView) findViewById(R.id.text_board_game_name);
        textViewGame.setText(game.getName());

        RatingBar wish_level_bar = (RatingBar) findViewById(R.id.rating_wish_details);
        wish_level_bar.setNumStars(game.getWish_level());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_board_game_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete_game) {
            // Confirm dialog for the delete action
            AlertDialog.Builder builder = new AlertDialog.Builder(BoardGameDetails.this);

            builder
                    .setMessage("Are you sure? ID: " + Integer.toString(game_id))
                    .setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // Delete the Board Game
                            BoardgamesDBHelper db = new BoardgamesDBHelper(BoardGameDetails.this);
                            db.deleteBoardGame(game_id);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    })
                    .show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
