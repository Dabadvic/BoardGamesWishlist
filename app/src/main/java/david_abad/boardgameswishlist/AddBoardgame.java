package david_abad.boardgameswishlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AddBoardgame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_boardgame);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button b_save = (Button)findViewById(R.id.save_button);
        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save
                Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                startActivity(intent);
            }
        });

    }

}
