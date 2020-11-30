package com.tictactoe;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board testBoard = new Board();
    Player[][] computerWinsBoard = {
            {Player.COMPUTER, Player.COMPUTER, Player.COMPUTER},
            {Player.USER, Player.USER, Player.USER},
            {Player.USER, Player.USER, Player.USER}
    };

    Player[][] userWinsBoard = {
            {Player.USER, Player.COMPUTER, Player.COMPUTER},
            {Player.USER, Player.COMPUTER, Player.COMPUTER},
            {Player.USER, Player.COMPUTER, Player.COMPUTER}
    };

    Player[][] drawFullBoard = {
            {Player.USER, Player.COMPUTER, Player.COMPUTER},
            {Player.COMPUTER,Player.USER,Player.USER},
            {Player.USER, Player.COMPUTER, Player.COMPUTER}
    };

    Player[][] emptyBoard = {
            {Player.NONE, Player.NONE, Player.NONE},
            {Player.NONE, Player.NONE, Player.NONE},
            {Player.NONE, Player.NONE, Player.NONE}
    };

    @Test
    void isRunning_1() {
        testBoard.setBoard(computerWinsBoard);
        assertFalse(testBoard.isRunning());
    }

    @Test
    void isRunning_2() {
        testBoard.setBoard(userWinsBoard);
        assertFalse(testBoard.isRunning());
    }

    @Test
    void isRunning_3() {
        testBoard.setBoard(drawFullBoard);
        assertFalse(testBoard.isRunning());
    }

    @Test
    void isRunning_4() {
        testBoard.setBoard(emptyBoard);
        assertTrue(testBoard.isRunning());
    }

    @Test
    void getEmptyTiles() {
    }

    @Test
    void move() {
    }

    @Test
    void getBestMove() {
    }

    @Test
    void displayBoard() {
    }

    @Test
    void isWinning() {
    }

    @Test
    void makeUserInput() {
    }

    @Test
    void setupBoard() {
    }

    @Test
    void returnMin() {
    }

    @Test
    void returnMax() {
    }

    @Test
    void callMinimax() {
    }

    @Test
    void minimax() {
    }

    @Test
    void getScanner() {
    }

    @Test
    void getRootValues() {
    }
}