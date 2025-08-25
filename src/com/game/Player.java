package com.game;

public enum Player {
    BLACK, WHITE;

    public Player opposite() {
        return this == BLACK ? WHITE : BLACK;
    }
}
