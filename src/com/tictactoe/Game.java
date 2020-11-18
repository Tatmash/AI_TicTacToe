package com.tictactoe;

import java.util.Random;

public class Game {

    private Board board;
    private Random random;

    public Game(){
        initializeGame();
        displayBoard();
        askQuestion();
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
        String userInput;
        String validatedUserInput;
        int columnInt = -1;
        int rowInt;
        while (board.isRunning()){
            System.out.println( "Make a move by entering the letter corresponding to your chosen column plus enter\n" +
                                "and the corresponding number to your chosen row plus enter.\n" +
                                "Please enter your move:");
            userInput = board.getScanner().next();
            validatedUserInput = validateInput(userInput);
            columnInt = getColumnInt(validatedUserInput);
            rowInt =  getRowInt(validatedUserInput);
            Tile userTile = new Tile( columnInt, rowInt);
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


    public String validateInput(String userInput){
        String twoChars;
        if(!(userInput.length() == 2)){
            twoChars = takeNewInput();
        }else{
            twoChars = userInput;
        }
        return letterPlusDigit(twoChars);
    }

    private String takeNewInput(){
        System.out.println("Please enter two characters, a letter and a number. \n(eg. for top left corner A1 + enter.)\n");
        String userInput = board.getScanner().next();
        if(!(userInput.length() == 2)){
            takeNewInput();
        }
        return userInput;
    }

    private String letterPlusDigit(String twoChars){
        String letter = null;
        String digit = null;
        if(Character.isLetter(twoChars.charAt(0)) && !Character.isLetter(twoChars.charAt(1))){
            letter = Character.toString(twoChars.charAt(0));
        }else if(Character.isLetter(twoChars.charAt(1)) && !Character.isLetter(twoChars.charAt(0))){
            letter = Character.toString(twoChars.charAt(1));
        }else{
            letterPlusDigit(takeNewInput());
        }
        if(Character.isDigit(twoChars.charAt(0)) && !Character.isDigit(twoChars.charAt(1))){
            digit = Character.toString(twoChars.charAt(0));
        }else if(Character.isDigit(twoChars.charAt(1)) && !Character.isDigit(twoChars.charAt(0))){
            digit = Character.toString(twoChars.charAt(1));
        }else{
            letterPlusDigit(takeNewInput());
        }
        return letter + digit;
    }

    private int getColumnInt(String validatedUserInput){
        int columnInt = -1;
        if(Character.compare(validatedUserInput.charAt(0), 'a') == 0 || Character.compare(validatedUserInput.charAt(0), 'A') == 0){
            columnInt = 0;
        }else if(Character.compare(validatedUserInput.charAt(0), 'b') == 0 || Character.compare(validatedUserInput.charAt(0), 'B') == 0){
            columnInt = 1;
        }else if(Character.compare(validatedUserInput.charAt(0), 'c') == 0 || Character.compare(validatedUserInput.charAt(0), 'C') == 0){
            columnInt = 2;
        }
        return columnInt;
    }

    private int getRowInt(String validatedUserInput) {
        return validatedUserInput.charAt(1) - 1;
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
