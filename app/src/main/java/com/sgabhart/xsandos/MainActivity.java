package com.sgabhart.xsandos;

import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private TicTacToe myGame;
    private ButtonGridAndTextView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myGame = new TicTacToe();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / TicTacToe.SIDE;
        ButtonHandler bh = new ButtonHandler();
        myView = new ButtonGridAndTextView(this, w, TicTacToe.SIDE, bh);
        myView.setStatusText(myGame.result());
        setContentView(myView);
    } // onCreate

    public void showNewGameDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("This is fun :)");
        alert.setMessage("Play again?");
        PlayDialog playAgain = new PlayDialog();
        alert.setPositiveButton("YES", playAgain);
        alert.setNegativeButton("NO", playAgain);
        alert.show();
    } // showNewGameDialog

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for (int row = 0; row < TicTacToe.SIDE; row++) {
                for (int col = 0; col < TicTacToe.SIDE; col++) {
                    if(myView.isButton((Button) v, row, col)){
                        int play = myGame.play(row, col);
                        if(play == 1) myView.setButtonText(row, col, "X");
                        else if(play == 2) myView.setButtonText(row, col, "O");
                        if(myGame.isGameOver()){
                            myView.setStatusBackgroundColor(Color.RED);
                            myView.enableButtons(false);
                            myView.setStatusText(myGame.result());
                            showNewGameDialog();
                        }
                    }
                }
            }
        } // onCLick
    } // ButtonHandler

    private class PlayDialog implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int id){
            if(id == -1){
                myGame.resetGame();
                myView.enableButtons(true);
                myView.resetButtons();
                myView.setStatusBackgroundColor(Color.GREEN);
                myView.setStatusText(myGame.result());
            } else if(id == -2) MainActivity.this.finish();
        }
    }
}
