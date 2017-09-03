package com.sgabhart.xsandos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Point;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
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
        gl.setRowCount(TicTacToe.SIDE);

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

        // Set gl as contentView
        setContentView(gl);
    } // buildGuiByCode

    public void update(int row, int column) {
        Log.w("MainActivity", "Inside update: " + row + ", " + column);
        buttons[row][column].setText("X");
    } //update

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            Log.w("MainActivity", "Inside onClck, v = " + v);
            for (int row = 0; row < TicTacToe.SIDE; row++) {
                for (int col = 0; col < TicTacToe.SIDE; col++) {
                    if(v == buttons[row][col]) update(row, col);
                }
            }
        } // onCLick
    } // ButtonHandler
}
