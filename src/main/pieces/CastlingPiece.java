package main.pieces;

import main.board.Board;
import utils.Coordinate;
import utils.Move;
import utils.SquareUtils;

public abstract class CastlingPiece extends Piece {
    protected boolean wasMoved;

    // Constructor
    protected CastlingPiece(boolean isWhite, int row, int col, boolean wasMoved) {
        super(isWhite, row, col);
        this.wasMoved = wasMoved;
    }

    // Concrete methods
    @Override
    public void moveTo(String squareName) {
        super.moveTo(squareName);
        wasMoved = true;
    }

    public void castle(Move castle) {
        Coordinate position = SquareUtils.getCoordinate(castle.destination());
        row = position.row();
        col = position.col();
    }

    // Abstract methods
    public abstract Move getCastlingMove(CastlingType type, final Board board);
}
