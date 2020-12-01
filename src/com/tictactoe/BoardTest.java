package com.tictactoe;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board testBoard = new Board();
    Player[][] computerWinsBoard = {
            {Player.COMPUTER, Player.COMPUTER, Player.COMPUTER},
            {Player.COMPUTER, Player.USER, Player.USER},
            {Player.USER, Player.USER, Player.COMPUTER}
    };

    Player[][] userWinsBoard = {
            {Player.USER, Player.COMPUTER, Player.USER},
            {Player.USER, Player.USER, Player.COMPUTER},
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

    Player[][] boardInUse = {
            {Player.USER, Player.USER, Player.COMPUTER},
            {Player.NONE, Player.NONE, Player.NONE},
            {Player.NONE, Player.NONE, Player.NONE}
    };

    @Test
    void isRunning() {
        testBoard.setBoard(computerWinsBoard);
        assertFalse(testBoard.isRunning());
        testBoard.setBoard(userWinsBoard);
        assertFalse(testBoard.isRunning());
        testBoard.setBoard(drawFullBoard);
        assertFalse(testBoard.isRunning());
        testBoard.setBoard(emptyBoard);
        assertTrue(testBoard.isRunning());
    }

    @Test
    void getEmptyTiles_1() {
        ArrayList<Tile> empty = new ArrayList<>();
        ArrayList<Tile> full = new ArrayList<>();
        ArrayList<Tile> halfFull = new ArrayList<>();
        empty.add(new Tile(0,0));
        empty.add(new Tile(0,1));
        empty.add(new Tile(0,2));
        empty.add(new Tile(1,0));
        empty.add(new Tile(1,1));
        empty.add(new Tile(1,2));
        empty.add(new Tile(2,0));
        empty.add(new Tile(2,1));
        empty.add(new Tile(2,2));
        halfFull.add(new Tile(1,0));
        halfFull.add(new Tile(1,1));
        halfFull.add(new Tile(1,2));
        halfFull.add(new Tile(2,0));
        halfFull.add(new Tile(2,1));
        halfFull.add(new Tile(2,2));
        testBoard.setBoard(emptyBoard);
        assertEquals(empty, testBoard.getEmptyTiles());
        testBoard.setBoard(boardInUse);
        assertEquals(halfFull, testBoard.getEmptyTiles());
        testBoard.setBoard(drawFullBoard);
        assertEquals(full, testBoard.getEmptyTiles());
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