package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b;
    EditText name1, name2;
    String namePlayer1, namePlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.button);
        name1 = findViewById(R.id.p1);
        name2 = findViewById(R.id.p2);
    }

    public void gameScreen(View view)
    {
        namePlayer1 = name1.getText().toString();
        namePlayer2 = name2.getText().toString();

        Intent intent = new Intent(this,game.class);
        intent.putExtra("namePlayer1",namePlayer1);
        intent.putExtra("namePlayer2",namePlayer2);
        startActivity(intent);
       // finish(); // can't go back to the screen
    }
}
