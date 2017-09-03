package com.sgabhart.xsandos;

/**
 * Created by Admin on 9/3/2017.
 */

public class TicTacToe {
    public static final int SIDE = 3;
    private int turn;
    private int[][] game;

    public TicTacToe() {
        game = new int[SIDE][SIDE];
        resetGame();
    } // constructor

    public int play(int row, int col) {
        int currentTurn = turn;
        if(row >= 0 && col >= 0 && row < SIDE && col < SIDE && game[row][col] == 0){
            game[row][col] = turn;
            if(turn == 1) turn = 2;
            else turn = 1;
            return currentTurn;
        }
        else return 0;
    } //play

    public int whoWon() {
        int rows = checkRows();
        if(rows > 0) return rows;

        int cols = checkColumns();
        if(cols > 0) return cols;

        int diags = checkDiagonals();
        if(diags > 0) return diags;

        return 0;
    } // whoWon

    protected int checkRows() {
        for (int row = 0; row < SIDE; row++) {
            if(game[row][0] != 0 && game[row][0] == game[row][1] && game[row][1] == game[row][2])
                return game[row][0];
        }
        return 0;
    } // checkRows

    protected int checkColumns() {
        for (int col = 0; col < SIDE; col++) {
            if(game[0][col] != 0 && game[0][col] == game[1][col] && game[1][col] == game[2][col])
                return game[0][col];
        }
        return 0;
    } // checkColumns

    protected int checkDiagonals() {
        if(game[0][0] != 0 && game[0][0] == game[1][1] && game[1][1] == game[2][2])
            return game[0][0];
        if(game[0][2] != 0 && game[2][0] == game[1][1] && game[1][1] == game[2][0])
            return game[2][0];
        return 0;
    } // checkDiagonals

    public boolean canNotPlay() {
        boolean result = true;
        for (int row = 0; row < SIDE; row++) {
            for (int col = 0; col < SIDE; col++) {
                if(game[row][col] == 0) result = false;
            }
        }

        return result;
    } // canNotPlay

    public boolean isGameOver() {
        return canNotPlay() || (whoWon() > 0);
    } //isGameOver

    public void resetGame() {
        for (int row = 0; row < SIDE; row++) {
            for (int col = 0; col < SIDE; col++) {
                game[row][col] = 0;
            }
        }

        turn = 1;
    } // resetGame
}
