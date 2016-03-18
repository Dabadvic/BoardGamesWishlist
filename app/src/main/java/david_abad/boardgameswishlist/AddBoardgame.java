package david_abad.boardgameswishlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class AddBoardgame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_boardgame);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button b_save = (Button)findViewById(R.id.save_button);
        if (b_save != null) {
            b_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get data
                    EditText editText = (EditText) findViewById(R.id.edit_text_name);
                    String name = editText.getText().toString();

                    RatingBar rBar = (RatingBar) findViewById(R.id.rating_wish);
                    int wish_level = (int) rBar.getRating();

                    Boardgame game = new Boardgame(name, wish_level);

                    // Save
                    BoardgamesDBHelper db = new BoardgamesDBHelper(AddBoardgame.this);
                    if (db.addBoardgame(game)) {
                        // Success
                        Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Couldn't add game", Toast.LENGTH_SHORT).show();
                    }

                    AddBoardgame.this.finish();
                }
            });
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
