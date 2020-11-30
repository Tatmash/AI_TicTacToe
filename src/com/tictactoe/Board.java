package com.tictactoe;
import java.util.*;

public class Board {
    private List<Tile> emptyTiles;
    private Scanner scanner;
    private Player[][] board;
    private List<Tile> rootValues;

    public Board(){
        initializeBoard();
    }

    private void initializeBoard() {
        this.rootValues = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.board = new Player[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
    }

    public boolean isRunning() {
        if( isWinning(Player.COMPUTER) ) {
            return false;
        }
        if( isWinning(Player.USER)) {
            return false;
        }
        if( getEmptyTiles().isEmpty() ) {
            return false;
        }
        return true;
    }

    public List<Tile> getEmptyTiles() {
        emptyTiles = new ArrayList<>();
        for (int i = 0; i < Constants.BOARD_SIZE; ++i) {
            for (int j = 0; j < Constants.BOARD_SIZE; ++j) {
                if (board[i][j] == Player.NONE) {
                    emptyTiles.add(new Tile(i, j));
                }
            }
        }
        return emptyTiles;
    }

    public void setBoard(Player[][] newBoard){
        this.board = newBoard;
    }

    public void move(Tile point, Player player) {
        board[point.getX()][point.getY()] = player;
    }

    public Tile getBestMove() {
        int max = Integer.MIN_VALUE;
        int best = Integer.MIN_VALUE;
        for (int i = 0; i < rootValues.size(); ++i) {
            if (max < rootValues.get(i).getMinimaxValue()) {
                max = rootValues.get(i).getMinimaxValue();
                best = i;
            }
        }
        return rootValues.get(best);
    }

    public void displayBoard() {
        System.out.println();
        System.out.println("  A "+"B "+"C");
        for (int i = 0; i < Constants.BOARD_SIZE; ++i) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < Constants.BOARD_SIZE; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isWinning(Player player) {
        // checking diagonals
        if ( board[0][0] == player && board[1][1] == player && board[2][2] == player ) {
            return true;
        }
        if( board[0][2] == player && board[1][1] == player && board[2][0] == player ){
            return true;
        }
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            // checking rows
            if ( board[i][0] == player && board[i][1] == player && board[i][2] == player ) {
                return true;
            }
            // checking columns
            if( board[0][i] == player && board[1][i] == player && board[2][i] == player ){
                return true;
            }
        }
        return false;
    }

    public void setupBoard() {
        for(int i=0;i<Constants.BOARD_SIZE;i++){
            for(int j=0;j<Constants.BOARD_SIZE;j++){
                board[i][j] = Player.NONE;
            }
        }
    }

    public int returnMin(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int index = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public int returnMax(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int index = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public void callMinimax(int depth, Player player){
        rootValues.clear();
        minimax(depth, player);
    }

    public int minimax(int depth, Player player) {
        if (isWinning(Player.COMPUTER)) return +1;
        if (isWinning(Player.USER)) return -1;
        List<Tile> availableCells = getEmptyTiles();
        //if there is no empty cell return 0 which means a draw
        if (availableCells.isEmpty()) return 0;
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < availableCells.size(); i++) {
            Tile point = availableCells.get(i);
            if (player == Player.COMPUTER) { //X's turn select the highest from below minimax() call
                move(point, Player.COMPUTER);
                int currentScore = minimax(depth + 1, Player.USER);
                scores.add(currentScore);
                if (depth == 0) {
                    point.setMinimaxValue(currentScore);
                    rootValues.add(point);
                }
            } else if (player == Player.USER) {//O's turn select the lowest from below minimax() call
                move(point, Player.USER);
                scores.add(minimax(depth + 1, Player.COMPUTER));
            }
            board[point.getX()][point.getY()] = Player.NONE; //Reset this point
        }
        if( player == Player.COMPUTER ){
            return returnMax(scores);
        }
        return returnMin(scores);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public List<Tile> getRootValues(){
        return this.rootValues;
    }
}
