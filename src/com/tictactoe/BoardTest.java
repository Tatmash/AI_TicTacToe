package com.tictactoe;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

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

    private Player[][] oneMoveMade = {
            {Player.COMPUTER, Player.NONE, Player.NONE},
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

    private List<Tile> fillUpList_RootValues(){
        List<Tile> result = new ArrayList<>();
        Tile one = new Tile(0,0,1);
        Tile two = new Tile(1,0,2);
        Tile three = new Tile(2,0,3);
        result.add(one);
        result.add(two);
        result.add(three);
        return result;
    }

    @Test
    void move() {
        Tile point = new Tile(0,0);
        assertThrows(NullPointerException.class, () -> testBoard.move(null, Player.COMPUTER),"Test_move_1");
        assertThrows(NullPointerException.class, () -> testBoard.move(point, null),"Test_move_2");
        testBoard.setBoard(emptyBoard);
        testBoard.move(point,Player.COMPUTER);
        Player[][] board1 = testBoard.getBoard();
        String playerOnTestBoard = board1[0][0].name();
        String playerOnOther = oneMoveMade[0][0].name();
        assertEquals(playerOnOther,playerOnTestBoard);
    }

    @Test
    void getBestMove() {
        Tile nullTile = null;
        Tile three = new Tile(2,0,3);
        List<Tile> rootValuesToAdd = fillUpList_RootValues();
        testBoard.setRootValues_forTest(rootValuesToAdd);
        int a = three.getMinimaxValue();
        int b = testBoard.getBestMove().getMinimaxValue();
        assertEquals(a,b);
        testBoard.emptyRootValues_forTest();
        assertThrows(NullPointerException.class, () -> testBoard.getBestMove());
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