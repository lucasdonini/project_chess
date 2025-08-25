package com;

import com.game.Game;

public class Tests {
    public static void main(String[] args) {
        Game game = new Game();
        game.begin();
        System.out.println(game);
        game.move("g1", "h3");
        System.out.println(game);
    }
}
