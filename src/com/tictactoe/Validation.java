package com.tictactoe;
import java.util.ArrayList;

class Validation {

    static boolean miracleFunction1(String userInput) {
        if(isLengthTwoDigits(userInput)){
            if(oneLetterOneDigit(userInput)){
                return true;
            }else{
                System.out.println("Mate, you need to enter two characters. A *letter* and a *number*.\n");
                return false;
            }
        }else{
            System.out.println("Mate, you need to enter *two* characters. A letter and a number.\n");
            return false;
        }
    }

    static String miracleFunction2(String userInput){
        if(rightOrder(userInput)){
            return userInput;
        }else{
            return changeOrderOfChars(userInput);
        }
    }

    static boolean miracleFunction3(String userInput){
        if(rightValues(userInput)){
            return true;
        }else{
            System.out.println("Listen, the coordinates are wrong. :(\n");
            return false;
        }
    }

    static boolean miracleFunction4(Board board, int column, int row){
        ArrayList<Tile> emptyTiles = (ArrayList<Tile>) board.getEmptyTiles();
        Tile candidateTile = new Tile(row, column);
        if(emptyTiles.contains(candidateTile)){
            emptyTiles.clear();
            return true;
        }
        System.out.println("Mate, that field is occupied.\n");
        return false;
    }

    private static boolean rightValues(String userInput) {
        String letter = String.valueOf(userInput.charAt(0));
        String digit = String.valueOf(userInput.charAt(1));
        return (letter.equals("a") || letter.equals("A") ||
                letter.equals("b") || letter.equals("B") ||
                letter.equals("c") || letter.equals("C")) &&
               (Integer.parseInt(digit) == 1 ||
                Integer.parseInt(digit) == 2 ||
                Integer.parseInt(digit) == 3);
    }

    private static String changeOrderOfChars(String userInput) {
        String letter = String.valueOf(userInput.charAt(1));
        String digit = String.valueOf(userInput.charAt(0));
        return letter + digit;
    }

    private static boolean rightOrder(String userInput) {
        return Character.isLetter(userInput.charAt(0)) && Character.isDigit(userInput.charAt(1));
    }

    private static boolean oneLetterOneDigit(String userInput) {
        return Character.isLetter(userInput.charAt(1)) && Character.isDigit(userInput.charAt(0)) ||
               Character.isLetter(userInput.charAt(0)) && Character.isDigit(userInput.charAt(1));
    }

    private static boolean isLengthTwoDigits(String userInput) {
        return userInput.length() == 2;
    }
}
