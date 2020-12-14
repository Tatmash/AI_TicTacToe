package com.tictactoe;

import java.util.Random;

public class Game {
    private Board board;
    private Random random;
    private boolean flag = false;

    public Game(){
        initializeGame();
        displayBoard();
        askQuestion();
        playGame();
        checkStatus();
    }

    private void initializeGame() {
        this.board = new Board();
        this.board.createEmptyBoard();
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
            getNewChoiceWhoStarts();
        }
    }

    private void getNewChoiceWhoStarts(){
        System.out.println("Please enter: \n1 for Computer or \n2 for Player.");
        int choice = board.getScanner().nextInt();
        if(choice == 1) {
            computerFirstMove();
        }
        if(!(choice == 1) && !(choice == 2)){
            getNewChoiceWhoStarts();
        }
    }

    private void computerFirstMove() {
        //random.nextInt(Constants.BOARD_SIZE) -> random number between 0 and boardSize
        Tile tile = new Tile(random.nextInt(Constants.BOARD_SIZE), random.nextInt(Constants.BOARD_SIZE));
        board.move(tile, Player.COMPUTER);
        board.displayBoard();
    }

    private void playGame(){
        String validatedUserInput;
        int columnInt;
        int rowInt;
        while (board.isRunning()){
            this.flag = false;
            validatedUserInput = takeCoordinates();
            columnInt = getColumnInt(validatedUserInput);
            rowInt = getRowInt(validatedUserInput);
            Tile userTile = new Tile( rowInt, columnInt);
            board.move(userTile, Player.USER);
            board.displayBoard();
            board.callMinimax(0, Player.COMPUTER);
            for (Tile tile : board.getRootValues()) {
                System.out.println("Field values: " + tile + "  " + tile.getMinimaxValue());
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

    private String takeCoordinates() {
        String result = null;
        while(!this.flag){
            System.out.println( "Make a move by entering the coordinates of your chosen field.\n" +
                    "Please enter your move:");
            String userInput = board.getScanner().next();
            if(Validation.miracleFunction1(userInput)){
                String rightOrder = Validation.miracleFunction2(userInput);
                if(Validation.miracleFunction3(rightOrder)){
                    int column = getColumnInt(rightOrder);
                    int row = getRowInt(rightOrder);
                    if(Validation.miracleFunction4(this.board, column, row)) {
                        result = rightOrder;
                        this.flag = true;
                    }
                }
            }
        }
        return result;
    }

    private int getColumnInt(String validatedUserInput){
        int columnInt = -1;
        if(validatedUserInput.charAt(0) == 'a' || validatedUserInput.charAt(0) == 'A'){
            columnInt = 0;
        }else if(validatedUserInput.charAt(0) == 'b' || validatedUserInput.charAt(0) == 'B'){
            columnInt = 1;
        }else if(validatedUserInput.charAt(0) == 'c' || validatedUserInput.charAt(0) == 'C'){
            columnInt = 2;
        }
        return columnInt;
    }

    private int getRowInt(String validatedUserInput) {
        return Integer.parseInt(String.valueOf(validatedUserInput.charAt(1))) -1;
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