package main.game;

public enum Player {
    BLACK("Black Pieces"),
    WHITE("White pieces");

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
