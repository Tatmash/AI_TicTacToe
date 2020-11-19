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

    private String takeCoordinates() {
        String result = null;
        while(!this.flag){
            System.out.println( "Make a move by entering the letter corresponding to your chosen column plus enter\n" +
                    "and the corresponding number to your chosen row plus enter.\n" +
                    "Please enter your move:");
            String userInput = board.getScanner().next();
            if(miracleFunction(userInput)){
                result = userInput;
                this.flag = true;
            }
        }
        return result;
    }

    //TODO validation should go to a separate class
    private boolean miracleFunction(String userInput) {
        if(isLengthTwoDigits(userInput)){
            if(oneLetterOneDigit(userInput)){
                if(rightOrder(userInput)){ //TODO it should recognize the coordinates in the wrong order too
                    if(rightValues(userInput)){ //TODO user should not be able to move twice to the same field
                        return true;
                    }else{
                        System.out.println("Listen, the coordinates are wrong. :(\n");
                        return false;
                    }
                }else{
                    if(rightValues(changeOrderOfChars(userInput))){
                        System.out.println("Listen, you need to enter the letter followed by a digit. :(\n");
                        return false;
                    }else{
                        System.out.println("Listen, the coordinates are wrong. :(\n");
                        return false;
                    }
                }
            }else{
                System.out.println("Mate, you need to enter two characters. A *letter* and a *number*.\n");
                return false;
            }
        }else{
            System.out.println("Mate, you need to enter *two* characters. A letter and a number.\n");
            return false;
        }
    }

    private boolean rightValues(String userInput) {
        String letter = String.valueOf(userInput.charAt(0));
        String digit = String.valueOf(userInput.charAt(1));
        if((letter.equals("a") || letter.equals("A")  ||
            letter.equals("b") || letter.equals("B")  ||
            letter.equals("c") || letter.equals("C")) &&
           (Integer.parseInt(digit) == 1 ||
            Integer.parseInt(digit) == 2 ||
            Integer.parseInt(digit) == 3)){
            return true;
        }
        return false;
    }

    private String changeOrderOfChars(String userInput) {
        String letter = String.valueOf(userInput.charAt(1));
        String digit = String.valueOf(userInput.charAt(0));
        return letter + digit;
    }

    private boolean rightOrder(String userInput) {
        if(Character.isLetter(userInput.charAt(0))){
            return true;
        }
        return false;
    }

    private boolean oneLetterOneDigit(String userInput) {
        //TODO can I reduce it to a single file?
        if (Character.isLetter(userInput.charAt(0)) && Character.isDigit(userInput.charAt(1))) {
            return true;
        } else if(Character.isLetter(userInput.charAt(1)) && Character.isDigit(userInput.charAt(0))) {
            return true;
        }
        return false;
    }

    private boolean isLengthTwoDigits(String userInput) {
        if(userInput.length() == 2){
            return true;
        }
        return false;
    }

    private String takeNewInput(){
        System.out.println("Please enter two characters, a letter and a number. \n(eg. for top left corner A1 + enter.)\n");
        String userInput = board.getScanner().next();
        if(!(userInput.length() == 2)){
            takeNewInput();
        }
        return userInput;
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
