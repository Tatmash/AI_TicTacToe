package com.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GameTest {

    Game testGame = new Game();

    @Test //(expected = )
    public void validateInput() {
        assertEquals("a1", testGame.validateInput("a1"));
    }
}