package com.sgabhart.xsandos;

/**
 * Created by Admin on 9/3/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class ButtonGridAndTextView extends GridLayout{
    private int side;
    private Button[][] buttons;
    private TextView status;

    public ButtonGridAndTextView (Context context, int width,
                                  int newSide, View.OnClickListener listener) {
        super(context);
        side = newSide;

        // Set # of rows, columns
        setColumnCount(side);
        setRowCount(side + 1);

        // Create buttons, add to layout
        buttons = new Button[side][side];
        for (int row = 0; row < side; row++) {
            for (int col = 0; col < side; col++) {
                buttons[row][col] = new Button(context);
                buttons[row][col].setTextSize((int)(width * .2));
                buttons[row][col].setOnClickListener(listener);
                addView(buttons[row][col], width, width);
            }
        }

        // Set layout parameters
        status = new TextView(context);
        GridLayout.Spec rowSpec = GridLayout.spec(side, 1);
        GridLayout.Spec colSpec = GridLayout.spec(0, side);
        GridLayout.LayoutParams lpStatus = new GridLayout.LayoutParams(rowSpec, colSpec);
        status.setLayoutParams(lpStatus);

        // Set status characteristics
        status.setWidth(side * width);
        status.setHeight(width);
        status.setGravity(Gravity.CENTER);
        status.setBackgroundColor(Color.GREEN);
        status.setTextSize((int)(width * .15));

        addView(status);
    } // constructor

    public void setStatusText(String text){
        status.setText(text);
    } // setStatusText

    public void setStatusBackgroundColor(int color){
        status.setBackgroundColor(color);
    } // setStatusBackgroundColor

    public void setButtonText(int row, int col, String text){
        buttons[row][col].setText(text);
    } // setButtonText

    public boolean isButton(Button b, int row, int col){
        return(b == buttons[row][col]);
    } // isButton

    public void resetButtons(){
        for (int row = 0; row < side; row++) {
            for (int col = 0; col < side; col++) {
                buttons[row][col].setText("");
            }
        }
    } // resetButtons

    public void enableButtons(boolean enabled){
        for (int row = 0; row < side; row++) {
            for (int col = 0; col < side; col++) {
                buttons[row][col].setEnabled(enabled);
            }
        }
    } // enableButtons
}
