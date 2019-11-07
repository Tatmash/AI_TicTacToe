package com.tictactoe;

import java.util.Random;

public class Game {

    private Board board;
    private Random random;

    public Game(){

        initializeGame();
        displayBoard();
        makeFirstMove();
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

    private void makeFirstMove() {

        System.out.println("Who would you like to make the first move?");
        System.out.println("1 - Computer");
        System.out.println("2 - Player");

        int choice = board.getScanner().nextInt();

        if(choice == 1){
            //random.nextInt(Constants.BOARD_SIZE) -> random number between 0 and boardSize
            Tile tile = new Tile(random.nextInt(Constants.BOARD_SIZE), random.nextInt(Constants.BOARD_SIZE));
            board.move(tile, Player.COMPUTER);
            board.displayBoard();
        }
    }

    private void playGame(){

        while (board.isRunning()){

            System.out.println("Please enter your move:");
            Tile userTile = new Tile(board.getScanner().nextInt(), board.getScanner().nextInt() );

            board.move(userTile, Player.USER);
            board.displayBoard();

            board.callMinimax(0, Player.COMPUTER);

            for (Tile tile : board.getRootValues()) {
                System.out.println("Tile values: " + tile + " minimaxValue: " + tile.getMinimaxValue());
            }

            board.move(board.getBestMove(), Player.COMPUTER);
            board.displayBoard();

            if (!board.isRunning()) {
                break;
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
