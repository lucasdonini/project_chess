package main.game;

import main.board.Board;
import main.board.Square;
import main.pieces.Piece;
import exception.EmptySelectionException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private Player player;
    private int turnCount;

    // Constructors
    public Game() {
        board = Board.setBoard();
        turnCount = 0;
    }

    // Other methods
    public void begin() {
        if (turnCount != 0) {
            throw new IllegalStateException("The game has already begun");
        }

        player = Player.WHITE;
        turnCount = 1;
    }

    public void nextTurn() {
        player = player.opposite();
    }

    private List<String> getPossibleDestinations(String selectedSquare) {
        List<String> result = new ArrayList<>();
        Piece piece = board.getPiece(selectedSquare);

        if (piece == null) {
            throw new EmptySelectionException("Empty square selected. No moves allowed.");
        }

        List<String> possibleMoves = piece.getPossibleMovements();

        for (String squareName : possibleMoves) {
            Square square = board.getSquare(squareName);
            Piece destination = square.getPiece();

            if (destination == null || !destination.belongsTo(player)) {
                result.add(squareName);
            }
        }

        return result;
    }

    public void move(String originSquareName, String destinationSquareName) {
        Square origin = board.getSquare(originSquareName);
        Square destination = board.getSquare(destinationSquareName);

        List<String> possibleDestinations = getPossibleDestinations(originSquareName);

        if (!possibleDestinations.contains(destinationSquareName)) {
            throw new IllegalArgumentException("Cannot get to destination square");
        }

        if (!origin.getPiece().belongsTo(player)) {
            throw new IllegalStateException("This piece doesn't belong to the current player");
        }

        origin.getPiece().moveTo(destinationSquareName);
        destination.setPiece(origin.getPiece());
        origin.setPiece(null);
    }

    @Override
    public String toString() {
        return String.format("%s\n\nCurrent Player: %s\n", board, player);
    }
}
