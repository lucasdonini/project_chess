package main.pieces;

public enum PieceType {
    KING(King.class),
    QUEEN(Queen.class),
    KNIGHT(Knight.class),
    BISHOP(Bishop.class),
    ROOK(Rook.class),
    PAWN(Pawn.class);

    private final Class<? extends Piece> realClass;

    PieceType(Class<? extends Piece> realClass) {
        this.realClass = realClass;
    }

    public Class<? extends Piece> getRealClass() {
        return realClass;
    }
}
