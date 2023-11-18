package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Interpolator;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class game extends AppCompatActivity implements View.OnClickListener {

    // Getting all the variables/ components required
    TextView score1, score2,status;
    TextView n1,n2;
    Button[] b = new Button[9];
    Button resetGame, playAgain;

    int p1, p2, rounds;
    boolean playerOnePlaying;

    int[] game = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    String namePlayer1, namePlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Getting the names of the players from the previous activity
        Intent intent = getIntent();
        namePlayer1 = intent.getStringExtra("namePlayer1");
        namePlayer2 = intent.getStringExtra("namePlayer2");


        n1 = findViewById(R.id.player1);
        n2 = findViewById(R.id.player2);

        //Displaying names of players
        n1.setText(namePlayer1);
        n2.setText(namePlayer2);

        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);
        status = findViewById(R.id.status);
        resetGame = findViewById(R.id.reset);
        playAgain = findViewById(R.id.playAgain);


        b[0] = findViewById(R.id.b0);
        b[1] = findViewById(R.id.b1);
        b[2] = findViewById(R.id.b2);
        b[3] = findViewById(R.id.b3);
        b[4] = findViewById(R.id.b4);
        b[5] = findViewById(R.id.b5);
        b[6] = findViewById(R.id.b6);
        b[7] = findViewById(R.id.b7);
        b[8] = findViewById(R.id.b8);


        // For associating each button with its onClick method
        // This allows the game to respond when a player clicks a button
        // The loop is simply a setup mechanism that ensures all buttons share the same onClick method,
        // making the code more concise and easier to maintain.

        for(int i=0; i<b.length; i++)
        {
            b[i].setOnClickListener(this);
        }

        p1=0;
        p2=0;
        rounds=0;
        playerOnePlaying=true;

    }

    @Override
    public void onClick(View v)
    {
        // In case the players try pressing a button which is already pressed
        if(!((Button)v).getText().toString().equals(""))
        {
            return;
        }
        else if (checkWinner()) // If there is already a winner
        {
            return;
        }

        //Getting the index of the button so that we can use it in changing the game array
        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameState = Integer.parseInt(buttonID.substring(buttonID.length()-1, buttonID.length()));

        if(playerOnePlaying)  // If player 1 is playing
        {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(ContextCompat.getColor(this,R.color.pink));
            ((Button) v).setTextSize(50);
            game[gameState] =0;
        }
        else      // If player 2 is playing
        {
            ((Button) v).setText("O");
            ((Button) v).setTextColor(ContextCompat.getColor(this,R.color.purple));
            ((Button) v).setTextSize(50);
            game[gameState]=1;
        }
        rounds++;

        if(checkWinner())
        {
            if(playerOnePlaying)
            {
                p1++;
                updateScore();
                status.setText(namePlayer1 + " --> WINNER !! ");
                status.setTextColor(ContextCompat.getColor(this,R.color.pink));
            }
            else
            {
                p2++;
                updateScore();
                status.setText(namePlayer2 + " --> WINNER !!");
                status.setTextColor(ContextCompat.getColor(this,R.color.purple));
            }
        }
        else if(rounds == 9) // All buttons have been pressed
        {
            status.setText("Tie --> No Winner");
        }
        else
        {
            playerOnePlaying = !playerOnePlaying;
        }

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                play();
            }
        });

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                play();
                p1=0;
                p2=0;
                updateScore();

            }
        });

    }

    private boolean checkWinner()  // Checks if there's a winner
    {
        boolean results = false;

        for(int[] winningPositions : winningPositions)
        {
            if(game[winningPositions[0]] == game[winningPositions[1]] &&
                    game[winningPositions[1]] == game[winningPositions[2]] &&
                    game[winningPositions[0]] != -1 )
            {
                results=true;
            }
        }

        return results;
    }

    private void updateScore()  // Updates scores
    {
        score1.setText(Integer.toString(p1));
        score2.setText(Integer.toString(p2));
    }

    private void play()  // Resetting the board -- but not the whole game
    {
        rounds=0;
        playerOnePlaying=true;
        for(int i=0; i<9;i++)
        {
            game[i] = -1;
            b[i].setText("");
        }
        status.setText("");
    }


}