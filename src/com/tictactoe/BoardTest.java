package com.tictactoe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board testBoard = new Board();
    private Player[][] computerWinsBoard_row = {
            {Player.COMPUTER, Player.COMPUTER, Player.COMPUTER},
            {Player.COMPUTER, Player.USER, Player.USER},
            {Player.USER, Player.USER, Player.COMPUTER}
    };
    private Player[][] computerWinsBoard_column = {
            {Player.COMPUTER, Player.COMPUTER, Player.USER},
            {Player.COMPUTER, Player.USER, Player.USER},
            {Player.COMPUTER, Player.USER, Player.COMPUTER}
    };
    private Player[][] computerWinsBoard_diagonal_1 = {
            {Player.COMPUTER, Player.COMPUTER, Player.USER},
            {Player.USER, Player.COMPUTER, Player.USER},
            {Player.COMPUTER, Player.USER, Player.COMPUTER}
    };
    private Player[][] computerWinsBoard_diagonal_2 = {
            {Player.USER, Player.COMPUTER, Player.COMPUTER},
            {Player.USER, Player.COMPUTER, Player.USER},
            {Player.COMPUTER, Player.USER, Player.COMPUTER}
    };
    private Player[][] userWinsBoard = {
            {Player.USER, Player.COMPUTER, Player.USER},
            {Player.USER, Player.USER, Player.COMPUTER},
            {Player.USER, Player.COMPUTER, Player.COMPUTER}
    };
    private Player[][] drawFullBoard = {
            {Player.USER, Player.COMPUTER, Player.COMPUTER},
            {Player.COMPUTER, Player.USER, Player.USER},
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

    private ArrayList<Tile> fillUpList_HalfFullBard() {
        ArrayList<Tile> result = new ArrayList<>();
        result.add(new Tile(1, 0));
        result.add(new Tile(1, 1));
        result.add(new Tile(1, 2));
        result.add(new Tile(2, 0));
        result.add(new Tile(2, 1));
        result.add(new Tile(2, 2));
        return result;
    }

    private ArrayList<Tile> fillUpList_EmptyBoard() {
        ArrayList<Tile> result = new ArrayList<>();
        result.add(new Tile(0, 0));
        result.add(new Tile(0, 1));
        result.add(new Tile(0, 2));
        result.add(new Tile(1, 0));
        result.add(new Tile(1, 1));
        result.add(new Tile(1, 2));
        result.add(new Tile(2, 0));
        result.add(new Tile(2, 1));
        result.add(new Tile(2, 2));
        return result;
    }

    private List<Tile> fillUpList_RootValues() {
        List<Tile> result = new ArrayList<>();
        Tile one = new Tile(0, 0, 1);
        Tile two = new Tile(1, 0, 2);
        Tile three = new Tile(2, 0, 3);
        result.add(one);
        result.add(two);
        result.add(three);
        return result;
    }

    private List<Integer> fillUpList_NonNullInteger() {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(2);
        result.add(3);

        return result;
    }

    private List<Integer> fillUpList_ExpectedScores_MiniMax() {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(2);
        result.add(3);
        return result;
    }

    @Test
    void isRunning() {
        testBoard.setBoard(computerWinsBoard_row);
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

    @Test
    void move() {
        Tile point = new Tile(0, 0);
        assertThrows(NullPointerException.class, () -> testBoard.move(null, Player.COMPUTER), "Test_move_1");
        assertThrows(NullPointerException.class, () -> testBoard.move(point, null), "Test_move_2");
        testBoard.setBoard(emptyBoard);
        testBoard.move(point, Player.COMPUTER);
        Player[][] board1 = testBoard.getBoard();
        String playerOnTestBoard = board1[0][0].name();
        String playerOnOther = oneMoveMade[0][0].name();
        assertEquals(playerOnOther, playerOnTestBoard);
    }

    @Test
    void getBestMove() {
        Tile three = new Tile(2, 0, 3);
        List<Tile> rootValuesToAdd = fillUpList_RootValues();
        testBoard.setRootValues_forTest(rootValuesToAdd);
        int a = three.getMinimaxValue();
        int b = testBoard.getBestMove().getMinimaxValue();
        assertEquals(a, b);
        testBoard.emptyRootValues_forTest();
        assertThrows(NullPointerException.class, () -> testBoard.getBestMove());
    }

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void displayBoard() {
        testBoard.setBoard(emptyBoard);
        testBoard.displayBoard();
        assertEquals("\n  A B C\n1 - - - \n2 - - - \n3 - - - \n\n", outputStreamCaptor.toString());
        Player[][] nullBoard = null;
        testBoard.setBoard(nullBoard);
        assertThrows(NullPointerException.class, () -> testBoard.displayBoard());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void isWinning() {
        Player[][] nullBoard = null;
        Player nullPlayer = null;
        testBoard.setBoard(nullBoard);
        assertThrows(NullPointerException.class, () -> testBoard.isWinning(Player.COMPUTER));
        assertThrows(NullPointerException.class, () -> testBoard.isWinning(Player.USER));
        assertThrows(NullPointerException.class, () -> testBoard.isWinning(nullPlayer));

        testBoard.setBoard(computerWinsBoard_column);
        assertTrue(testBoard.isWinning(Player.COMPUTER));

        testBoard.setBoard(computerWinsBoard_row);
        assertTrue(testBoard.isWinning(Player.COMPUTER));

        testBoard.setBoard(computerWinsBoard_diagonal_1);
        assertTrue(testBoard.isWinning(Player.COMPUTER));

        testBoard.setBoard(computerWinsBoard_diagonal_2);
        assertTrue(testBoard.isWinning(Player.COMPUTER));
    }

    @Test
    void createEmptyBoard() {
        String emptyField = "NONE";
        Player[][] boardToCompare = testBoard.getBoard();
        testBoard.createEmptyBoard();
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                assertEquals(emptyField, boardToCompare[i][j].name());
            }
        }
    }

    @Test
    void returnMin() {
        List<Integer> nullList = null;
        List<Integer> nonNullList = fillUpList_NonNullInteger();
        assertThrows(NullPointerException.class, () -> testBoard.returnMin(nullList));
        assertEquals(1, testBoard.returnMin(nonNullList));
    }

    @Test
    void returnMax() {
        List<Integer> nullList = null;
        List<Integer> nonNullList = fillUpList_NonNullInteger();
        assertThrows(NullPointerException.class, () -> testBoard.returnMin(nullList));
        assertEquals(3, testBoard.returnMax(nonNullList));
    }

    @Test
    void callMinimax() {
        Player nullPlayer = null;
        List<Tile> rootValues_1 = fillUpList_RootValues();
        List<Tile> rootValues_2 = fillUpList_RootValues();
        rootValues_1.clear();
        testBoard.setRootValues_forTest(rootValues_2);
        testBoard.callMinimax(0, Player.COMPUTER);
        List<Tile> rootValues_3 = testBoard.getRootValues();
        assertEquals(rootValues_1, rootValues_3);
        assertThrows(IllegalArgumentException.class, () -> testBoard.callMinimax(0, Player.USER));
        assertThrows(IllegalArgumentException.class, () -> testBoard.callMinimax(0, Player.NONE));
        assertThrows(NullPointerException.class, () -> testBoard.callMinimax(0, null));
        assertThrows(NullPointerException.class, () -> testBoard.callMinimax(0, nullPlayer));
    }

    @Test
    void minimax() {
        Player nullPlayer = null;
        assertThrows(NullPointerException.class, () -> testBoard.minimax(0, null));
        assertThrows(NullPointerException.class, () -> testBoard.minimax(0, nullPlayer));
        assertThrows(IllegalArgumentException.class, () -> testBoard.minimax(0, Player.NONE));
        assertThrows(IllegalArgumentException.class, () -> testBoard.minimax(-1, Player.USER));
        testBoard.setBoard(boardInUse);
        assertEquals(1, testBoard.minimax(0, Player.COMPUTER));
    }
}