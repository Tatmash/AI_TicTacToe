package com.tictactoe;

public enum TileState {

    COMPUTER("X"), USER("O"), EMPTY("-");
    private final String state;

    private TileState(String state) {
        this.state = state;
    }

    @Override
    public String toString() { return this.state; }
}
