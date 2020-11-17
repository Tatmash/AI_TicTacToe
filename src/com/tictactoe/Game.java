/**
 * dlksfgjsd'ihgsd'iofjghsd'fig'sdfijhg
 *
 *
 * */
package com.tictactoe;

import java.util.Random;

public class Game {

    private Board board;
    private Random random;

    public Game(){
        initializeGame();
        displayBoard();
        askQuestion();
        //computerFirstMove();
        playGame();
        checkStatus();
    }

    private void initializeGame() {
        this.board = new Board();
        this.board.setupBoard();
        this.random = new Random();
    }

    private void displayBoard() {
        board.displayBoard();
    }

    private void askQuestion(){
        System.out.println("Who would you like to make the first move?");
        System.out.println("1 - Computer");
        System.out.println("2 - Player");
        int choice = board.getScanner().nextInt();
        if(choice == 1) {
            computerFirstMove();
        }
        if(!(choice == 1) && !(choice == 2)){
            wrongPick();
        }
    }

    private void wrongPick(){
        System.out.println("Please enter: \n1 for Computer or \n2 for Player.");
        int choice = board.getScanner().nextInt();
        if(choice == 1) {
            computerFirstMove();
        }
        if(!(choice == 1) && !(choice == 2)){
            wrongPick();
        }
    }

    private void computerFirstMove() {
        //random.nextInt(Constants.BOARD_SIZE) -> random number between 0 and boardSize
        Tile tile = new Tile(random.nextInt(Constants.BOARD_SIZE), random.nextInt(Constants.BOARD_SIZE));
        board.move(tile, Player.COMPUTER);
        board.displayBoard();
    }

    private void playGame(){
        char columnChar;
        int columnInt = -1;
        int rowInt = -1;
        while (board.isRunning()){
            System.out.println( "Make a move by entering the letter corresponding to your chosen column plus enter\n" +
                                "and the corresponding number to your chosen row plus enter.\n" +
                                "Please enter your move:");
            columnChar = board.getScanner().next().charAt(0);
            if(Character.compare(columnChar, 'a') == 0 || Character.compare(columnChar, 'A') == 0){
                columnInt = 0;
            }else if(Character.compare(columnChar, 'b') == 0 || Character.compare(columnChar, 'B') == 0){
                columnInt = 1;
            }else if(Character.compare(columnChar, 'c') == 0 || Character.compare(columnChar, 'C') == 0){
                columnInt = 2;
            }
            rowInt =  board.getScanner().nextInt() - 1;
            Tile userTile = new Tile( rowInt, columnInt);
            board.move(userTile, Player.USER);
            board.displayBoard();
            board.callMinimax(0, Player.COMPUTER);
            for (Tile tile : board.getRootValues()) {
                System.out.println("Tile values: " + tile + " minimaxValue: " + tile.getMinimaxValue());
            }
            if(board.getEmptyTiles().size() != 0) {
                board.move(board.getBestMove(), Player.COMPUTER);
                board.displayBoard();
                if (!board.isRunning()) {
                    break;
                }
            }
        }
    }

    private void checkStatus(){
        if( board.isWinning(Player.COMPUTER)){
            System.out.println("Computer won.");
        }else if (board.isWinning(Player.USER)){
            System.out.println("Player won.");
        }else{
            System.out.println("It is a draw.");
        }
    }
}
