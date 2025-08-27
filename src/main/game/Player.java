package main.game;

import utils.TerminalUtils;

public enum Player {
    BLACK(TerminalUtils.inRed("Black")),
    WHITE("White");

    private final String displayName;

    Player(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public Player opposite() {
        return this == BLACK ? WHITE : BLACK;
    }
}
