package com.sgabhart.xsandos;

import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons;
    private TicTacToe myGame;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myGame = new TicTacToe();
        buildGuiByCode();
    }

    public void buildGuiByCode() {
        // Get width of screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / TicTacToe.SIDE;

        // Create layout manager
        GridLayout gl = new GridLayout(this);
        gl.setColumnCount(TicTacToe.SIDE);
        gl.setRowCount(TicTacToe.SIDE + 1);

        // Create buttons, add to layout
        buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];
        ButtonHandler bh = new ButtonHandler();
        for (int row = 0; row < TicTacToe.SIDE; row++) {
            for (int col = 0; col < TicTacToe.SIDE; col++) {
                buttons[row][col] = new Button(this);
                buttons[row][col].setTextSize((int)(.2 * w));
                buttons[row][col].setOnClickListener(bh);
                gl.addView(buttons[row][col], w, w);
            }
        }

        // Set layout parameters for 4th row
        status = new TextView(this);
        GridLayout.Spec rowSpec = GridLayout.spec(TicTacToe.SIDE, 1);
        GridLayout.Spec colSpec = GridLayout.spec(0, TicTacToe.SIDE);
        GridLayout.LayoutParams lpStatus = new GridLayout.LayoutParams(rowSpec, colSpec);
        status.setLayoutParams(lpStatus);

        // Set up status characteristics
        status.setWidth(TicTacToe.SIDE * w);
        status.setHeight(w);
        status.setGravity(Gravity.CENTER);
        status.setBackgroundColor(Color.GREEN);
        status.setTextSize((int)(.15 * w));
        status.setText(myGame.result());

        gl.addView(status);

        // Set gl as contentView
        setContentView(gl);
    } // buildGuiByCode

    public void update(int row, int column) {
        int play = myGame.play(row, column);
        if(play == 1) buttons[row][column].setText("X");
        else if(play == 2) buttons[row][column].setText("O");
        if(myGame.isGameOver()) {
            status.setBackgroundColor(Color.RED);
            enableButtons(false);
            status.setText(myGame.result());
            showNewGameDialog();
        }
    } //update

    public void enableButtons(boolean enabled) {
        for (int row = 0; row < TicTacToe.SIDE; row++) {
            for (int col = 0; col < TicTacToe.SIDE; col++) {
                buttons[row][col].setEnabled(enabled);
            }
        }
    } // enableButtons

    public void resetButtons() {
        for (int row = 0; row < TicTacToe.SIDE; row++) {
            for (int col = 0; col < TicTacToe.SIDE; col++) {
                buttons[row][col].setText("");
            }
        }
    } // resetButtons

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
            Log.w("MainActivity", "Inside onClck, v = " + v);
            for (int row = 0; row < TicTacToe.SIDE; row++) {
                for (int col = 0; col < TicTacToe.SIDE; col++) {
                    if(v == buttons[row][col]) update(row, col);
                }
            }
        } // onCLick
    } // ButtonHandler

    private class PlayDialog implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int id){
            if(id == -1){
                myGame.resetGame();
                enableButtons(true);
                resetButtons();
                status.setBackgroundColor(Color.GREEN);
                status.setText(myGame.result());
            } else if(id == -2) MainActivity.this.finish();
        }
    }
}
