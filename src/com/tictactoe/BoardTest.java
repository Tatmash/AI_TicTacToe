package com.tictactoe;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board testBoard = new Board();
    private Player[][] computerWinsBoard = {
            {Player.COMPUTER, Player.COMPUTER, Player.COMPUTER},
            {Player.COMPUTER, Player.USER, Player.USER},
            {Player.USER, Player.USER, Player.COMPUTER}
    };

    private Player[][] userWinsBoard = {
            {Player.USER, Player.COMPUTER, Player.USER},
            {Player.USER, Player.USER, Player.COMPUTER},
            {Player.USER, Player.COMPUTER, Player.COMPUTER}
    };

    private Player[][] drawFullBoard = {
            {Player.USER, Player.COMPUTER, Player.COMPUTER},
            {Player.COMPUTER,Player.USER,Player.USER},
            {Player.USER, Player.COMPUTER, Player.COMPUTER}
    };

    private Player[][] emptyBoard = {
            {Player.NONE, Player.NONE, Player.NONE},
            {Player.NONE, Player.NONE, Player.NONE},
            {Player.NONE, Player.NONE, Player.NONE}
    };

    private Player[][] boardInUse = {
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
    void getEmptyTiles() {
        ArrayList<Tile> emptyTilesOnFullBoard = new ArrayList<>();
        ArrayList<Tile> emptyTilesOnEmptyBoard;
        ArrayList<Tile> emptyTilesOnHalfFullBoard;
        emptyTilesOnEmptyBoard = fillUpList_EmptyBoard();
        emptyTilesOnHalfFullBoard = fillUpList_HalfFullBard();
        testBoard.setBoard(emptyBoard);
        assertEquals(emptyTilesOnEmptyBoard, testBoard.getEmptyTiles());
        testBoard.setBoard(boardInUse);
        assertEquals(emptyTilesOnHalfFullBoard, testBoard.getEmptyTiles());
        testBoard.setBoard(drawFullBoard);
        assertEquals(emptyTilesOnFullBoard, testBoard.getEmptyTiles());
    }

    private ArrayList<Tile> fillUpList_HalfFullBard() {
        ArrayList<Tile> result = new ArrayList<>();
        result.add(new Tile(1,0));
        result.add(new Tile(1,1));
        result.add(new Tile(1,2));
        result.add(new Tile(2,0));
        result.add(new Tile(2,1));
        result.add(new Tile(2,2));
        return result;
    }

    private ArrayList<Tile> fillUpList_EmptyBoard() {
        ArrayList<Tile> result = new ArrayList<>();
        result.add(new Tile(0,0));
        result.add(new Tile(0,1));
        result.add(new Tile(0,2));
        result.add(new Tile(1,0));
        result.add(new Tile(1,1));
        result.add(new Tile(1,2));
        result.add(new Tile(2,0));
        result.add(new Tile(2,1));
        result.add(new Tile(2,2));
        return result;
    }

//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @Test
//    public void throwsNothing() {
//        // no exception expected, none thrown: passes.
//    }
//
    @Test
    void move_1() {
        //thrown.expect(NullPointerException.class);
        Tile point = new Tile(0,0);
        assertThrows(NullPointerException.class, () -> testBoard.move(null, Player.COMPUTER),"blabla");
    }

    @Test
    void move_2() {
        //thrown.expect(NullPointerException.class);
        Tile point = new Tile(0,0);
        assertThrows(NullPointerException.class, () -> testBoard.move(point, null),"blabla");
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

    @Test
    void setBoard() {
    }
}