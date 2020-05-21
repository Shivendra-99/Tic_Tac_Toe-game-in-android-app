package com.example.tic_tac_toe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons=new Button[3][3];
    private boolean playerTrun=true;
    private  int roundCount;
    private int player1Point;
    private int player2Point;
    private TextView player1;
    private TextView player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1=findViewById(R.id.text_view_p1);
        player2=findViewById(R.id.text_view_p2);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String b="button_"+i+j;
                int resId=getResources().getIdentifier(b,"id",getPackageName());
                buttons[i][j]=findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button ButtonReset=findViewById(R.id.button_reset);
        ButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (playerTrun) {
            ((Button) v).setText("x");
        } else {
            ((Button) v).setText("o");
        }
        roundCount++;
        if (checkForWin()) {
            if (playerTrun) {
                player1Win();
            } else {
                player2Win();
            }
        } else if (roundCount == 9) {
            draw();
        }
        else
        {
            playerTrun=!playerTrun;
        }
    }
    private boolean checkForWin()
    {
        String[][] field=new String[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))
            {
                return true;
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
            {
                return true;
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals(""))
            {
                return true;
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
            {
                return true;
            }
        }
      return false;
    }
    private void player1Win()
    {
        player1Point++;
        Toast.makeText(this,"Player 1 Wins",Toast.LENGTH_SHORT).show();
        UpdatePointText();
        resetBorad();
    }
    private  void player2Win()
    {
      player2Point++;
      Toast.makeText(this,"Player 2 Wins",Toast.LENGTH_SHORT).show();
      UpdatePointText();
      resetBorad();
    }
    private void draw()
    {
       Toast.makeText(this,"DRAW!",Toast.LENGTH_SHORT).show();
       resetBorad();
    }
    private void UpdatePointText()
    {
        player1.setText("Player 1"+player1Point);
        player2.setText("Player 2"+player2Point);
    }
    private  void resetBorad()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
              buttons[i][j].setText("");
            }
        }
        roundCount=0;
        playerTrun=true;
    }
    private void resetGame()
    {
        player1Point=0;
        player2Point=0;
        UpdatePointText();
        resetBorad();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount",roundCount);
        outState.putInt("PlayerPoint1",player1Point);
        outState.putInt("PlayerPoint2",player2Point);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount=savedInstanceState.getInt("RoundCount");
        player1Point=savedInstanceState.getInt("PlayerPoint1");
        player2Point=savedInstanceState.getInt("player2Point");
        playerTrun=savedInstanceState.getBoolean("PlayerTrun");

    }
}
