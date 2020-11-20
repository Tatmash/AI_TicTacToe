package com.tictactoe;

import java.util.Objects;

public class Tile {

    private int x;
    private int y;
    private int minimaxValue;

    public Tile(int column, int row){

        this.x = column;
        this.y = row;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMinimaxValue() {
        return minimaxValue;
    }

    public void setMinimaxValue(int score) {
        this.minimaxValue = score;
    }

    @Override
    public String toString(){
        return "("+this.x+","+this.y+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;
        Tile tile = (Tile) o;
        return getX() == tile.getX() &&
                getY() == tile.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}



